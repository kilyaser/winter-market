package ru.geelbrains.spring.winter.market.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private String categoryTitle;
    private int height;
    private int weight;
    private String description;
    private String image;

}
