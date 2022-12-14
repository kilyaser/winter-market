package ru.geelbrains.spring.winter.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.entities.Product;

import java.util.List;

@Component
@AllArgsConstructor
@Getter
public class Cart {

    private List<Product> products;

}
