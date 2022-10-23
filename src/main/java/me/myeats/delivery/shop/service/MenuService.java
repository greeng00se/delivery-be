package me.myeats.delivery.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.common.exception.shop.ShopNotFoundException;
import me.myeats.delivery.shop.domain.Menu;
import me.myeats.delivery.shop.domain.MenuRepository;
import me.myeats.delivery.shop.domain.Shop;
import me.myeats.delivery.shop.domain.ShopRepository;
import me.myeats.delivery.shop.dto.MenuDto;
import me.myeats.delivery.shop.dto.request.MenuSaveRequestDto;
import me.myeats.delivery.shop.dto.response.MenuSearchResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

    private final ShopRepository shopRepository;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public void save(Long shopId, MenuSaveRequestDto menuSaveRequestDto, Long ownerId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(ShopNotFoundException::new);
        if (!shop.isOwnedBy(ownerId)) {
            throw new UnauthorizedException();
        }
        List<Menu> menus = menuSaveRequestDto.getMenus()
                .stream()
                .map(menuDto -> menuMapper.toMenu(menuDto, ownerId))
                .collect(toList());
        menuRepository.saveAll(menus);
    }

    public MenuSearchResponseDto search(Long shopId, Long ownerId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(ShopNotFoundException::new);
        if (!shop.isOwnedBy(ownerId)) {
            throw new UnauthorizedException();
        }
        List<Menu> menus = menuRepository.findAllByShopId(shopId);
        List<MenuDto> menuDtos = menus.stream()
                .map(menuMapper::toMenuDto)
                .collect(toList());
        return new MenuSearchResponseDto(menuDtos.size(), menuDtos);
    }
}
