package me.myeats.delivery.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.shop.domain.MenuRepository;
import me.myeats.delivery.shop.dto.MenuSaveRequestDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Long save(Long id, MenuSaveRequestDto menuSaveRequestDto) {
        return 1L;
    }
}
