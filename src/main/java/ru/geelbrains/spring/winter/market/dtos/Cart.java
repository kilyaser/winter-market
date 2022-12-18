package ru.geelbrains.spring.winter.market.dtos;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.geelbrains.spring.winter.market.entities.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        boolean containsProduct = containsItem(product.getId());
        if (items.isEmpty() || !containsProduct) {
            items.add(mapToCartItem(product));
        } else {
            for (CartItem cartItem : items) {
                if (cartItem.getProductId().equals(product.getId())) {
                    int quantity = cartItem.getQuantity() + 1;
                    int price = cartItem.getPricePerProduct() * quantity;
                    cartItem.setQuantity(quantity);
                    cartItem.setPrice(price);
                }
            }
        }
        recalculate();
    }
    public void remove(Long id) {
        if (containsItem(id)) {
            Optional<Integer> indexOptional = getContainsIndex(id);
            if (indexOptional.isPresent()) {
                int index = indexOptional.get();
                int quantity = items.get(index).getQuantity();
                if (quantity > 1) {
                    quantity = quantity - 1;
                    items.get(index).setQuantity(quantity);
                    recalculatePrice(index);
                } else {
                    items.remove(index);
                }
            }
        }
        recalculate();
    }
    public void removeAllQuantity(Long id) {
        items.removeIf(p -> p.getProductId().equals(id));
        recalculate();
    }

    public void cleanAll() {
        items.clear();
        recalculate();
    }

    private void recalculatePrice(int index) {
        int price = items.get(index).getPricePerProduct() * items.get(index).getQuantity();
        items.get(index).setPrice(price);
    }
    private void recalculate() {
        totalPrice = 0;
        totalPrice =  items.stream().mapToInt(CartItem::getPrice).sum();
    }
    private CartItem mapToCartItem(Product product) {
        return new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
    }

    public boolean containsItem(Long id) {
      return items.stream().anyMatch(i -> i.getProductId().equals(id));
    }
    public Optional<Integer> getContainsIndex(Long id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(id)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }


}
