package ru.geelbrains.spring.winter.market.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;
    private int height;
    private int weight;
    private String description;
    private String image;

}
