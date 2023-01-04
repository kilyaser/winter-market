package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.converters.OrderConverter;
import ru.geelbrains.spring.winter.market.dtos.OrderDto;
import ru.geelbrains.spring.winter.market.entities.User;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geelbrains.spring.winter.market.models.OrderData;
import ru.geelbrains.spring.winter.market.repositories.OrderRepository;
import ru.geelbrains.spring.winter.market.servicies.OrderService;
import ru.geelbrains.spring.winter.market.servicies.UserService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatOrder(Principal principal, @RequestBody(required = false) OrderData orderData) {
        log.info("Сработа orderController user {}", principal.getName());
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        orderService.creatOrder(user);
    }

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        log.info("getAll orders controllers was required");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        return orderService.getAllOrdersByUser(user).stream().map(orderConverter::entityToDto).toList();
    }

}
