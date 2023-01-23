package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.geelbrains.spring.winter.market.converters.CartConverter;
import ru.geelbrains.spring.winter.market.dtos.CartDto;
import ru.geelbrains.spring.winter.market.repositories.ProductRepository;
import ru.geelbrains.spring.winter.market.servicies.CartService;
import ru.geelbrains.spring.winter.market.utils.StringResponse;

import java.security.Principal;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final ProductRepository productRepository;
    private final CartConverter cartConverter;
    private final CartService cartService;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.add(targetUuid, id);
    }
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@Nullable Principal principal, @PathVariable String uuid) {
        String targetUuid = getCartUuid(principal, uuid);
        log.info("cart controller uuid: {}", targetUuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }
    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductFromCart(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.deleteProductFromCart(targetUuid, id);
    }

    @GetMapping("/{uuid}/deleteQuantity/{id}")
    public void deleteAllQuantity(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.deleteAllQuantity(targetUuid, id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.clear(targetUuid);
    }

    private String getCartUuid(Principal principal, String uuid) {
        if (Objects.nonNull(principal)) {
            return principal.getName();
        }
        return uuid;
    }

}
