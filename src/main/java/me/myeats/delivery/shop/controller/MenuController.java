package me.myeats.delivery.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.shop.dto.MenuSaveRequestDto;
import me.myeats.delivery.shop.service.MenuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/{id}/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public Long save(@PathVariable Long id,
                     @RequestBody MenuSaveRequestDto menuSaveRequestDto) {
        log.info(menuSaveRequestDto.toString());
        return menuService.save(id, menuSaveRequestDto);
    }
}
