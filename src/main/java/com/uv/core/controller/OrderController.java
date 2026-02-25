package com.uv.core.controller;

import com.uv.core.model.Orders;
import com.uv.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");
        return repository.save(order);
    }
}
