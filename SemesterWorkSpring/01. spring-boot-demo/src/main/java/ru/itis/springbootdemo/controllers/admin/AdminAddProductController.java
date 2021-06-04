package ru.itis.springbootdemo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.dto.ProductDto;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.CategoriesRepository;
import ru.itis.springbootdemo.repositories.ProductRepository;
import ru.itis.springbootdemo.services.FileStorageService;

import java.util.List;

@Controller
public class AdminAddProductController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CategoriesRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/addProducts")
    public String getAddProductsPage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);;
        return "admin/addProduct";
    }

    @PostMapping("/addProducts")
    public String addProduct(@RequestParam("file") MultipartFile file, ProductDto productDto) {
        String fileName = fileStorageService.saveFile(file);
        System.out.println("Категория, которую выбрали: " + productDto.getCategoryId());
        Product product = Product.builder()
                .imageName(fileName)
                .name(productDto.getName())
                .categoryId(productDto.getCategoryId())
                .description(productDto.getDescription())
                .build();

        productRepository.save(product);
        return "redirect:/adminCategories/" + productDto.getCategoryId();
    }
}
