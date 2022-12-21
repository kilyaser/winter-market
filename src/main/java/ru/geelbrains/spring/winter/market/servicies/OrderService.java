package ru.geelbrains.spring.winter.market.servicies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geelbrains.spring.winter.market.converters.OrderItemConverter;
import ru.geelbrains.spring.winter.market.entities.Order;
import ru.geelbrains.spring.winter.market.entities.OrderItem;
import ru.geelbrains.spring.winter.market.entities.User;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geelbrains.spring.winter.market.models.Cart;
import ru.geelbrains.spring.winter.market.repositories.OrderItemRepository;
import ru.geelbrains.spring.winter.market.repositories.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartService cartService;
    private final OrderItemConverter orderItemConverter;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    public Order creatOrder(User user) {
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);
        List<OrderItem> items = cart.getItems().stream().map(oi -> orderItemConverter.cartItemToOrderItem(oi, order)).toList();
        orderItemRepository.saveAll(items);
        order.setItems(items);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Order id %s not found", order.getId())));

        //формирование ордера
    }
}
