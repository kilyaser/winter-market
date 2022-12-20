package ru.geelbrains.spring.winter.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.dtos.OrderDto;
import ru.geelbrains.spring.winter.market.dtos.OrderItemDto;
import ru.geelbrains.spring.winter.market.entities.Order;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        List<OrderItemDto> items = order.getItems().stream().map(orderItemConverter::entityToDto).toList();
        return new OrderDto(order.getId(), order.getTotalPrice(), items);
    }
}
