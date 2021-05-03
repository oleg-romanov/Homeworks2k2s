package ru.itis.springbootdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.CategoriesPage;
import ru.itis.springbootdemo.dto.CategoryDto;
import ru.itis.springbootdemo.dto.ProductDto;
import ru.itis.springbootdemo.dto.ProductsPage;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.ProductsRepository;
import ru.itis.springbootdemo.services.ProductsService;

import static ru.itis.springbootdemo.dto.ProductDto.from;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository servicesRepository;

    @Override
    public ProductsPage search(Integer size, Integer page, String query, String sortParameter, String directionParameter, Long categoryId) {
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
        Page<Product> productsPage = servicesRepository.search(query, pageRequest, categoryId);
        return ProductsPage.builder()
                .pagesCount(productsPage.getTotalPages())
                .products(ProductDto.from(productsPage.getContent()))
                .build();
    }
}
