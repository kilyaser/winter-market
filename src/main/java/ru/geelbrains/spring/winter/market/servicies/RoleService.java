package ru.geelbrains.spring.winter.market.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geelbrains.spring.winter.market.entities.Role;
import ru.geelbrains.spring.winter.market.entities.User;
import ru.geelbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geelbrains.spring.winter.market.repositories.RoleRepository;
import ru.geelbrains.spring.winter.market.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

}