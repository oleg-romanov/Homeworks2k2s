package ru.itis.springbootdemo.services;

import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.ProductsPage;

@Service
public interface ProductsService {
    ProductsPage search(Integer size, Integer page, String query, String sort, String direction, Long categoryId);
}
