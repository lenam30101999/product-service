package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<CategoriesAttribute, Long> {
    CategoriesAttribute save(CategoriesAttribute categoriesAttribute);

    CategoriesAttribute findCategoriesAttributeById(Long id);



    List<CategoriesAttribute> findAllByCategories(Categories categories);

    void deleteById(Long id);
}
