package ru.geelbrains.spring.winter.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private List<CartItemDto> items;
    private int totalPrice;
}

