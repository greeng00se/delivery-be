package me.myeats.delivery.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myeats.delivery.common.jwt.customer.CurrentCustomer;
import me.myeats.delivery.common.jwt.customer.CustomerPreAuthorized;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.order.dto.CartDto;
import me.myeats.delivery.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @CustomerPreAuthorized
    @PostMapping("/{shopId}")
    public ResponseEntity<Void> order(@RequestBody CartDto cartDto, @PathVariable Long shopId,
                                      @CurrentCustomer Customer customer) {
        orderService.order(cartDto, shopId, customer.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long orderId) {
        orderService.pay(orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/{orderId}/delivery")
    public ResponseEntity<Void> delivery(@PathVariable Long orderId) {
        orderService.delivery(orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
