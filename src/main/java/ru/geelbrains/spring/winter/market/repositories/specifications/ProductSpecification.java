package ru.geelbrains.spring.winter.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geelbrains.spring.winter.market.entities.Product;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> priceGranderOrEqualsThen(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualsThen(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
