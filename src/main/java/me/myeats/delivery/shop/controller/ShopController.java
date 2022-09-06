package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.shop.dto.ShopSaveDto;
import me.myeats.delivery.shop.service.ShopService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public Long save(@Valid @RequestBody ShopSaveDto.Request saveDto) {
        return shopService.save(saveDto);
    }
}
