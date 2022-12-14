package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.servicies.CartService;
import ru.geelbrains.spring.winter.market.servicies.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@Slf4j
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public List<Product> addToCart(@PathVariable Long id) {
        return cartService.addProductToCart(productService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public void deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteProductFromCart(id);
    }
    @GetMapping
    public List<Product> getAllProductFromCart() {
        return cartService.getProductFromCart();
    }

    @GetMapping("/sum")
    public int getCartSum() {
        return cartService.getSum();
    }
}
