package ru.geelbrains.spring.winter.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.dtos.OrderItemDto;
import ru.geelbrains.spring.winter.market.entities.Order;
import ru.geelbrains.spring.winter.market.entities.OrderItem;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geelbrains.spring.winter.market.models.CartItem;
import ru.geelbrains.spring.winter.market.servicies.ProductService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final ProductService productService;

    public OrderItem cartItemToOrderItem(CartItem cartItem, Order order) {
        var product = productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Product id: %s not found", cartItem.getProductId())));
        return new OrderItem(null,
                product,
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getId(), o.getProduct().getTitle(), o.getProduct().getImage(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
