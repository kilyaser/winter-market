package ru.geelbrains.spring.winter.market.converters;

import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.dtos.ProductDto;
import ru.geelbrains.spring.winter.market.entities.Product;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
