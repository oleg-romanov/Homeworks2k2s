package ru.itis.springbootdemo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.dto.CategoryDto;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.repositories.ProductRepository;
import ru.itis.springbootdemo.services.FileStorageService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminEditCategoriesController {

    @Autowired
    private CategoriesRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/adminEditCategories/{categoryId}")
    public String getEditCategoriesPage(@PathVariable Long categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("error", "Ошибка! Требуется id");
            return "errorPage";
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(!optionalCategory.isPresent()) {
            model.addAttribute("error", "Ошибка! Not found");
            return "errorPage";
        }
        Category category = optionalCategory.get();
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @PostMapping("/adminEditCategories/{categoryId}")
    public String editCategory(@RequestParam("file") MultipartFile file, @PathVariable Long categoryId, Model model, CategoryDto categoryDto) {
        if(categoryId == null) {
            model.addAttribute("error", "Ошибка! Требуется id");
            return "errorPage";
        }
        String fileName = fileStorageService.saveFile(file);

        Category category = Category.builder()
                .id(categoryId)
                .imageName(fileName)
                .name(categoryDto.getName())
                .build();
        categoryRepository.save(category);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/admin/adminCategories";
    }
}
