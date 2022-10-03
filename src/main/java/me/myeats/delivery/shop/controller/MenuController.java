package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.jwt.owner.CurrentOwner;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.shop.dto.request.MenuSaveRequestDto;
import me.myeats.delivery.shop.service.MenuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/{shopId}/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public void save(@PathVariable Long shopId,
                     @Valid @RequestBody MenuSaveRequestDto menuSaveRequestDto,
                     @CurrentOwner Owner owner) {
        log.info(menuSaveRequestDto.toString());
        menuService.save(shopId, menuSaveRequestDto, owner.getId());
    }
}
