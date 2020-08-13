package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Optional<Product> findById(Long id);

    Product findByIdAndUserId(long id, long userId);

    void deleteById(Long id);

    List<Product> findAllByCategories(Categories categories);
}
