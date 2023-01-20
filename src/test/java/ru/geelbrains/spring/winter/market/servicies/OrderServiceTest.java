package ru.geelbrains.spring.winter.market.servicies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geelbrains.spring.winter.market.entities.*;
import ru.geelbrains.spring.winter.market.models.Cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @MockBean
    CartService cartService;

    @Test
    void creatOrder() {
        CartService mock = Mockito.mock(CartService.class);
        User user = getUserForTest(1L);
        Mockito.when(mock.getCurrentCart(user.getUsername())).thenReturn(getCurrentCartForTest());



        given(cartService.getCurrentCart(user.getUsername())).willReturn(getCurrentCartForTest());

        orderService.creatOrder(user);
        List<Order> orders = orderService.getAllOrdersByUser(user);
        Assertions.assertNotNull(orders);
        for (Order order: orders) {
            Assertions.assertNotNull(order);
            Assertions.assertNotNull(order.getId());
            Assertions.assertEquals(getCurrentCartForTest().getTotalPrice(), order.getTotalPrice().setScale(2, RoundingMode.HALF_UP));

        }


    }

    private static User getUserForTest(Long id) {
        User user = new User();
        user.setId(id);
        user.setPassword("100" + id);
        user.setUsername("testUser" + id);
        return user;
    }

    private static Cart getCurrentCartForTest() {
        Cart cart = new Cart();
        cart.add(getProductForTest(1L));
        cart.add(getProductForTest(2L));
        cart.add(getProductForTest(3L));
        return cart;
    }

    private static Product getProductForTest(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setTitle("BearTest" + id);
        product.setCategory(new Category());
        product.setPrice(new BigDecimal(500));
        product.setHeight(30);
        product.setWeight(20);
        product.setDescription("High quality print");
        product.setImage("images/cart/bear.jpg");
        return product;
    }
}