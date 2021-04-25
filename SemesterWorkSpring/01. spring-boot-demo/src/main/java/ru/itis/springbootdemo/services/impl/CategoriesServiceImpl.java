package ru.itis.springbootdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.CategoriesPage;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.services.CategoriesService;

import static ru.itis.springbootdemo.dto.CategoryDto.from;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository servicesRepository;

    @Override
    public CategoriesPage search(Integer size, Integer page, String query, String sortParameter, String directionParameter) {
        Direction direction = Direction.ASC;
        Sort sort = Sort.by(direction, "id");

        if (directionParameter != null) {
            direction = Direction.fromString(directionParameter);
        }

        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }

        if (query == null) {
            query = "empty";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Category> categoriesPage = servicesRepository.search(query, pageRequest);
        return CategoriesPage.builder()
                .pagesCount(categoriesPage.getTotalPages())
                .categories(from(categoriesPage.getContent()))
                .build();
    }
}
