package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.owner.CurrentOwner;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.shop.dto.ShopSaveRequestDto;
import me.myeats.delivery.shop.dto.ShopSearchResponseDto;
import me.myeats.delivery.shop.service.ShopService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public Long save(@Valid @RequestBody ShopSaveRequestDto requestDto,
                     @CurrentOwner Owner owner) {
        return shopService.save(requestDto, owner.getId());
    }

    @GetMapping
    public ShopSearchResponseDto search(@CurrentOwner Owner owner) {
        return shopService.search(owner.getId());
    }
}
