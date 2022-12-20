package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.models.Cart;
import ru.geelbrains.spring.winter.market.servicies.CartService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    private final CartService cartService;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }
    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }
    @GetMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteProductFromCart(id);
    }

    @GetMapping("/deleteQuantity/{id}")
    public void deleteAllQuantity(@PathVariable Long id) {
        cartService.deleteAllQuantity(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

}
