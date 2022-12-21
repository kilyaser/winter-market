package ru.geelbrains.spring.winter.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geelbrains.spring.winter.market.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
