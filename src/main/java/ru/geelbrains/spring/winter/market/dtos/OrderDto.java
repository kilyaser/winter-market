package ru.geelbrains.spring.winter.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
    private List<OrderItemDto> items;


}
