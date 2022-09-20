package me.myeats.delivery.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopSaveRequestDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public Long save(ShopSaveRequestDto requestDto, Owner owner) {
        Shop shop = Shop.builder()
                .address(requestDto.getAddress())
                .minOrderAmount(Money.wons(requestDto.getMinOrderAmount()))
                .phoneNumber(requestDto.getPhoneNumber())
                .open(true)
                .ownerId(owner.getId())
                .build();

        return shopRepository.save(shop).getId();
    }
}
