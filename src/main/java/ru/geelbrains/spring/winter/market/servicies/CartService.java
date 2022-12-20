package ru.geelbrains.spring.winter.market.servicies;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geelbrains.spring.winter.market.models.Cart;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    public void add(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Can't add product to the cart, product id:%s not found", id)));
        tempCart.add(product);
        log.info("tempCart size after adding " + tempCart.getItems().size());
    }

    public void deleteProductFromCart(Long id) {
        tempCart.remove(id);
    }
    public void deleteAllQuantity(Long id) {
        tempCart.removeAllQuantity(id);
    }
    public Cart getCurrentCart() {
        return tempCart;
    }
    public void clear() {
        tempCart.clear();
    }

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }
}
