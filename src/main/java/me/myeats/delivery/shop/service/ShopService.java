package me.myeats.delivery.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.jwt.JwtSecurityUtil;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final OwnerRepository ownerRepository;

    public Long save(ShopSaveDto.Request saveDto) {
        String name = JwtSecurityUtil.getCurrentUsername().get();
        Owner owner = ownerRepository.findOneWithAuthoritiesByName(name).get();

        Shop shop = Shop.builder()
                .address(saveDto.getAddress())
                .minOrderAmount(Money.wons(saveDto.getMinOrderAmount()))
                .phoneNumber(saveDto.getPhoneNumber())
                .open(true)
                .ownerId(owner.getId())
                .build();

        return shopRepository.save(shop).getId();
    }
}
