package ru.geelbrains.spring.winter.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geelbrains.spring.winter.market.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
