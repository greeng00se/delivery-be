package me.myeats.delivery.shop.domain;

import me.myeats.delivery.shop.dto.ShopDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("select new me.myeats.delivery.shop.dto.ShopDto(s.name, s.open, s.minOrderAmount, s.address, s.phoneNumber) " +
            "from Shop s where s.ownerId = :ownerId")
    List<ShopDto> findShopDtoListByOwnerId(Long ownerId);
}
