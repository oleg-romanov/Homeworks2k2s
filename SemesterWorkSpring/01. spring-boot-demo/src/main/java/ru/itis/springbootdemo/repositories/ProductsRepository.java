package ru.itis.springbootdemo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.springbootdemo.models.Category;
import ru.itis.springbootdemo.models.Product;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    @Query("select product from Product product where (:q = 'empty' or UPPER(product.name) like UPPER(concat('%', :q, '%'))) and product.categoryId = :categoryId")
    Page<Product> search(@Param("q") String q, Pageable pageable, @Param("categoryId") Long categoryId);
}
