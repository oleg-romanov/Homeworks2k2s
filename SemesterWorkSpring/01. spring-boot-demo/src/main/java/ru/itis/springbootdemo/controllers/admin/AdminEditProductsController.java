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
import ru.itis.springbootdemo.models.Product;
import ru.itis.springbootdemo.repositories.ProductRepository;
import ru.itis.springbootdemo.services.FileStorageService;

import java.util.Optional;

@Controller
public class AdminEditProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/adminEditProducts/{categoryId}")
    public String getEditProductsPage(@PathVariable Long categoryId, Model model) {
        System.out.println(categoryId);
        if(categoryId == null) {
            model.addAttribute("error", "Ошибка! Требуется id");
            return "errorPage";
        }
        Optional<Product> optionalProduct = productRepository.findProductById(categoryId);
        if(!optionalProduct.isPresent()) {
            model.addAttribute("error", "Ошибка! Not found");
            return "errorPage";
        }
        Product product = optionalProduct.get();
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @PostMapping("/adminEditProducts/{productId}")
    public String editProduct(@RequestParam("file") MultipartFile file, @PathVariable Long productId, Model model, ProductDto productDto) {
        if(productId ==  null) {
            model.addAttribute("error", "Ошибка! Требуется id");
            return "errorPage";
        }

        String fileName = fileStorageService.saveFile(file);

        System.out.println("описание с формы: " + productDto.getDescription());
        Product product = Product.builder()
                .imageName(fileName)
                .name(productDto.getName())
                .categoryId(productDto.getId())
                .description(productDto.getDescription())
                .build();
        productRepository.save(product);
        return "redirect:/adminCategories/" + product.getCategoryId();
    }
}
