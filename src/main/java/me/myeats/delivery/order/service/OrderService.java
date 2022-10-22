package me.myeats.delivery.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.exception.order.InvalidOrderException;
import me.myeats.delivery.common.exception.order.OrderNotFoundException;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderRepository;
import me.myeats.delivery.order.dto.CartDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public void order(CartDto cartDto, Long shopId, Long id) {
        if (!Objects.equals(cartDto.getShopId(), shopId) ||
                !Objects.equals(cartDto.getUserId(), id)) {
            throw new InvalidOrderException();
        }
        Order order = orderMapper.toOrder(cartDto);
        orderRepository.save(order);
    }

    @Transactional
    public void pay(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.paid();
    }

    @Transactional
    public void delivery(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.delivered();
    }
}
