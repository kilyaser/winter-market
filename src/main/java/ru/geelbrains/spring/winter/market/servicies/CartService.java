package ru.geelbrains.spring.winter.market.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geelbrains.spring.winter.market.dtos.Cart;
import ru.geelbrains.spring.winter.market.entities.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;

    public List<Product> getProductFromCart() {
        return cart.getProducts();
    }

    public int getSum() {
        if (cart.getProducts().isEmpty()) {
            return 0;
        } else {
            return cart.getProducts().stream().mapToInt(Product::getPrice).sum();
        }
    }

    public List<Product> addProductToCart(Product product) {
        cart.getProducts().add(product);
        return cart.getProducts();
    }

    public void deleteProductFromCart(Long id) {
       for (int i = 0; i < cart.getProducts().size(); i++) {
           if (cart.getProducts().get(i).getId().equals(id)) {
               cart.getProducts().remove(i);
               break;
           }
       }
    }
}
