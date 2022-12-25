package ru.geelbrains.spring.winter.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geelbrains.spring.winter.market.dtos.CartDto;
import ru.geelbrains.spring.winter.market.models.Cart;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).toList());
        return cartDto;
    }

}
