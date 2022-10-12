package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.jwt.owner.CurrentOwner;
import me.myeats.delivery.common.jwt.owner.OwnerPreAuthorized;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.shop.dto.request.MenuSaveRequestDto;
import me.myeats.delivery.shop.dto.response.MenuSearchResponseDto;
import me.myeats.delivery.shop.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@OwnerPreAuthorized
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/{shopId}/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Void> save(@PathVariable Long shopId,
                                     @Valid @RequestBody MenuSaveRequestDto menuSaveRequestDto,
                                     @CurrentOwner Owner owner) {
        menuService.save(shopId, menuSaveRequestDto, owner.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public MenuSearchResponseDto search(@PathVariable Long shopId, @CurrentOwner Owner owner) {
        return menuService.search(shopId, owner.getId());
    }
}
