package ru.geelbrains.spring.winter.market.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geelbrains.spring.winter.market.entities.Category;
import ru.geelbrains.spring.winter.market.repositories.CategoryRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

}
