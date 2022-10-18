package me.myeats.delivery.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.order.domain.Order;
import me.myeats.delivery.order.domain.OrderRepository;
import me.myeats.delivery.order.dto.CartDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public void order(CartDto cartDto) {
        Order order = orderMapper.toOrder(cartDto);
        orderRepository.save(order);
    }
}
