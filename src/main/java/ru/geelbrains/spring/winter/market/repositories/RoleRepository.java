package ru.geelbrains.spring.winter.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geelbrains.spring.winter.market.entities.Role;
import ru.geelbrains.spring.winter.market.entities.User;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String username);
}
