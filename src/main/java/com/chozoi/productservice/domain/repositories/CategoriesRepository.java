package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Categories save(Categories categories);

    Categories findCategoriesById(Long id);

    List<Categories> findCategoryByIdNotNull();

    List<Categories> findAllByParentId(Long parentId);

    List<Categories> findAll();

    void deleteById(Long id);
}
