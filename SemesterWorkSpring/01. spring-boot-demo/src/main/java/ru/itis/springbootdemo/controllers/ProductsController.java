package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.ProductsPage;
import ru.itis.springbootdemo.services.ProductsService;

@RestController
public class ProductsController {
    @Autowired
    private ProductsService productsService;

//    @GetMapping("/products")
//    public String getProductsPage() {
//
//        return "products";
//    }

    @GetMapping("/categories/{categoryId}/productsList/search")
    @CrossOrigin(origins = "http://localhost:80")
    public ResponseEntity<ProductsPage> search(@RequestParam("size") Integer size,
                                               @RequestParam("page") Integer page,
                                               @RequestParam(value = "q", required = false) String query,
                                               @RequestParam(value = "sort", required = false) String sort,
                                               @RequestParam(value = "direction", required = false) String direction,
                                               @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productsService.search(size, page, query, sort, direction, categoryId));
    }
}
