package ru.geelbrains.spring.winter.market.dtos;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Cart {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
    }

}
