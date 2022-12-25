package ru.geelbrains.spring.winter.market.converters;

import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.dtos.CartItemDto;
import ru.geelbrains.spring.winter.market.models.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
        return cartItemDto;
    }
}
