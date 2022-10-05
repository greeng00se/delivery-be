package me.myeats.delivery.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.money.Money;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.ShopDto;
import me.myeats.delivery.shop.dto.request.ShopSaveRequestDto;
import me.myeats.delivery.shop.dto.response.ShopSearchResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public Long save(ShopSaveRequestDto requestDto, Long ownerId) {
        Shop shop = Shop.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .minOrderAmount(Money.wons(requestDto.getMinOrderAmount()))
                .phoneNumber(requestDto.getPhoneNumber())
                .open(true)
                .ownerId(ownerId)
                .build();

        return shopRepository.save(shop).getId();
    }

    public ShopSearchResponseDto search(Long ownerId) {
        List<ShopDto> shops = shopRepository.findShopDtoListByOwnerId(ownerId);

        return ShopSearchResponseDto.builder()
                .shops(shops)
                .size(shops.size())
                .build();
    }
}
