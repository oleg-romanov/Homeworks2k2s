package ru.itis.springbootdemo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.dto.CategoryDto;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.services.FileStorageService;

import java.util.List;

@Controller
public class AdminAddCategoryController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CategoriesRepository categoryRepository;

    @GetMapping("/addCategories")
    public String getAddCategoriesPage() {
        return "admin/addCategory";
    }

    @PostMapping("/addCategories")
    public String addCategory(@RequestParam("file") MultipartFile file, CategoryDto categoryDto, Model model) {
        String fileName = fileStorageService.saveFile(file);
        Category category = Category.builder()
                .imageName(fileName)
                .name(categoryDto.getName())
                .build();
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/adminCategories";
    }
}
