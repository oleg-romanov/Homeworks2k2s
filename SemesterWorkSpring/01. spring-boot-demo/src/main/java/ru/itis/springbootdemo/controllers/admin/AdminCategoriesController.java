package ru.itis.springbootdemo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminCategoriesController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoryRepository;

    @GetMapping("/adminCategories/{categoryId}")
    public String getAdminCategoriesPage(@PathVariable Long categoryId, Model model) {
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
                model.addAttribute("error", "Ошибка, в этой категории еще нет товаров, хотите добавить?");
                return "errorPage";
            }
            return "admin/adminProducts";
        }
        return "/admin/adminCategories";
    }

    @GetMapping("/adminCategories")
    public String getCategoriesPage1(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/admin/adminCategories";
    }

    @Transactional
    @ResponseBody
    @PostMapping("/adminCategories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        if (categoryId == null) {
            //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
//        if (request.getParameter("productId") != null){
//            Long id = Long.parseLong(request.getParameter("productId"));
//            productRepository.deleteProductById(id);
//            return;
//        }
//        productRepository.deleteProductsByCategoryId(categoryId);
        categoryRepository.deleteCategoryById(categoryId);
    }
}
