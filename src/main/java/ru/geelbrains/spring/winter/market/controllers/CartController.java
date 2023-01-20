package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.converters.CartConverter;
import ru.geelbrains.spring.winter.market.dtos.CartDto;
import ru.geelbrains.spring.winter.market.servicies.CartService;
import ru.geelbrains.spring.winter.market.utils.StringResponse;

import java.security.Principal;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final CartConverter cartConverter;
    private final CartService cartService;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        log.info("cart controller uuid: {}", targetUuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }
    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteProductFromCart(targetUuid, id);
    }

    @GetMapping("/{uuid}/deleteQuantity/{id}")
    public void deleteAllQuantity(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteAllQuantity(targetUuid, id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.clear(targetUuid);
    }

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }

}
