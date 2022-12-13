package ru.geelbrains.spring.winter.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geelbrains.spring.winter.market.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
