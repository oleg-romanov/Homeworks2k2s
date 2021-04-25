package ru.itis.springbootdemo.services;

import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.CategoriesPage;

@Service
public interface CategoriesService {
    CategoriesPage search(Integer size, Integer page, String query, String sort, String direction);
}
