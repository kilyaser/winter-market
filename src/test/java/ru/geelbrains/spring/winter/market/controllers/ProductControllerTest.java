package ru.geelbrains.spring.winter.market.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geelbrains.spring.winter.market.dtos.ProductDto;
import ru.geelbrains.spring.winter.market.entities.Product;
import ru.geelbrains.spring.winter.market.repositories.ProductRepository;
import ru.geelbrains.spring.winter.market.servicies.ProductService;
import java.math.BigDecimal;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    void findProductById() {
        ProductDto product = getProductDto();
        Product savedProduct = productService.createNewProduct(product);

        Assertions.assertNotNull(savedProduct);
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertNotNull(savedProduct.getTitle());

        ProductDto productByHttp = webTestClient.get()
                .uri("/api/v1/products/" + savedProduct.getId())
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();


        Assertions.assertEquals(savedProduct.getId(), productByHttp.getId());
        Assertions.assertEquals(savedProduct.getTitle(), productByHttp.getTitle());

    }
    @Test
    void findProductByIdNotFount() {
        productRepository.deleteAll();

        webTestClient.get()
                .uri("/api/v1/products/1")
                .exchange()
                .expectStatus().isNotFound();
    }
    @Test
    void createNewProduct() {
        ProductDto dto = getProductDto();

        ProductDto dtoByHttp = webTestClient.post()
                .uri("/api/v1/products")
                .bodyValue(dto)
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(dtoByHttp);
        Assertions.assertNotNull(dtoByHttp.getId());
        Assertions.assertNotNull(dtoByHttp.getTitle());
        Assertions.assertEquals(dto.getTitle(), dtoByHttp.getTitle());
        Assertions.assertEquals(dto.getCategoryTitle(), dtoByHttp.getCategoryTitle());

    }

    @Test
    void deleteProductById() {
        ProductDto dto = getProductDto();
        Product savedProduct = productService.createNewProduct(dto);

        Assertions.assertNotNull(savedProduct);
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertEquals(dto.getTitle(), savedProduct.getTitle());

        webTestClient.get()
                .uri("/api/v1/product" + savedProduct.getId())
                .exchange()
                .expectBody(ProductDto.class);


        webTestClient.delete()
                .uri("/api/v1/products/" + savedProduct.getId())
                .exchange()
                .expectStatus().isOk();


        webTestClient.get()
                .uri("/api/v1/products/" + savedProduct.getId())
                .exchange()
                .expectStatus().isNotFound();

    }

    private static ProductDto getProductDto() {
        ProductDto dto = new ProductDto();
        dto.setTitle("BearTest");
        dto.setCategoryTitle("Original Art");
        dto.setPrice(BigDecimal.valueOf(500.00));
        dto.setHeight(30);
        dto.setWeight(20);
        dto.setDescription("High quality print");
        dto.setImage("images/cart/bear.jpg");
        return dto;
    }
}