package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geelbrains.spring.winter.market.converters.ProductConverter;
import ru.geelbrains.spring.winter.market.servicies.ProductService;
import ru.geelbrains.spring.winter.market.soap.products.GetAllProductsRequest;
import ru.geelbrains.spring.winter.market.soap.products.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.winter-market.com/spring/products";
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().stream().map(productConverter::entityToSoapDto).forEach(response.getProducts()::add);
        return response;
    }
}
