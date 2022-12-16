package ru.geelbrains.spring.winter.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.servicies.ProductService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts() {
       return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findById(id).get(); //FIXME: написать правельное извлечение объекта из Optional
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
