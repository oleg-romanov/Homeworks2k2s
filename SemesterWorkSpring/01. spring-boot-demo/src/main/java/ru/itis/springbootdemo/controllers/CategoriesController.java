package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.CategoriesPage;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.repositories.ProductRepository;
import ru.itis.springbootdemo.services.CategoriesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoriesController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoryRepository;

    @GetMapping("/categories/{categoryId}")
    public String getCategoriesPage(@PathVariable Long categoryId, Model model) {
        if (categoryId == null) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
        } else {
            Optional<Category> category = categoryRepository.findById(categoryId);
            List<Product> products = productRepository.findProductsByCategoryId(categoryId);
            category.ifPresent(value -> model.addAttribute("name", value.getName()));
            if (products.size() != 0) {
                model.addAttribute("products", products);
            } else {
//                List<Product> productss = new ArrayList<>();
//                productss.add(Product
//                        .builder()
//                        .categoryId(categoryId)
//                        .build());
//                model.addAttribute("products", productss);
                model.addAttribute("error", "Ошибка, в этой категории еще нет товаров");
                return "errorPage";
            }

            return "products";
        }
        return "categories";
    }

    @GetMapping("/categories")
    public String getCategoriesPage1(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @Autowired
    private CategoriesService categoriesService;


    @GetMapping("/categoriesList/search")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:80")
    public ResponseEntity<CategoriesPage> search(@RequestParam("size") Integer size,
                                                 @RequestParam("page") Integer page,
                                                 @RequestParam(value = "q", required = false) String query,
                                                 @RequestParam(value = "sort", required = false) String sort,
                                                 @RequestParam(value = "direction", required = false) String direction) {
        return ResponseEntity.ok(categoriesService.search(size, page, query, sort, direction));
    }
}
