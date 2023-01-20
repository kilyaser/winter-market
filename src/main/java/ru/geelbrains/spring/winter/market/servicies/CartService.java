package ru.geelbrains.spring.winter.market.servicies;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geelbrains.spring.winter.market.models.Cart;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductService productService;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    public void add(String uuid, Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Can't add product to the cart, product id:%s not found", id)));
        getCurrentCart(uuid).add(product);
        log.info("tempCart size after adding " + getCurrentCart(uuid).getItems().size());
    }

    public void deleteProductFromCart(String uuid, Long id) {
        getCurrentCart(uuid).remove(id);
    }
    public void deleteAllQuantity(String uuid, Long id) {
        getCurrentCart(uuid).removeAllQuantity(id);
    }
    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!carts.containsKey(targetUuid)) {
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }
    public void clear(String uuid) {
        getCurrentCart(uuid).clear();
    }

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }
}
