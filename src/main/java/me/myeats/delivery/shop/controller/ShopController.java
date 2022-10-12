package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.owner.CurrentOwner;
import me.myeats.delivery.common.jwt.owner.OwnerPreAuthorized;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.shop.dto.request.ShopSaveRequestDto;
import me.myeats.delivery.shop.dto.response.ShopSearchResponseDto;
import me.myeats.delivery.shop.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@OwnerPreAuthorized
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ShopSaveRequestDto requestDto,
                                     @CurrentOwner Owner owner) {
        shopService.save(requestDto, owner.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ShopSearchResponseDto search(@CurrentOwner Owner owner) {
        return shopService.search(owner.getId());
    }
}
