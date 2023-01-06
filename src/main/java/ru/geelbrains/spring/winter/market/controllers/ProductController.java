package ru.geelbrains.spring.winter.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.converters.ProductConverter;
import ru.geelbrains.spring.winter.market.dtos.ProductDto;
import ru.geelbrains.spring.winter.market.entities.Category;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geelbrains.spring.winter.market.servicies.ProductService;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    private final ProductConverter productConverter;
    private final ProductService productService;
    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
       log.info("min_price {}, max_price {}, title_part {}", minPrice, maxPrice, titlePart);
        if (page < 1) {
            page = 1;
        }
       return productService.findAll(minPrice, maxPrice, titlePart, page).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        log.info("product id: {}", id);
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product id:%s not found", id)));
        return productConverter.entityToDto(p);
    }
    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
