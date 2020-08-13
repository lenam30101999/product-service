package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeValueRepository extends JpaRepository<CategoriesAttributeValue, Long> {
    CategoriesAttributeValue save(CategoriesAttributeValue value);

    List<CategoriesAttributeValue> findAllByAttribute(CategoriesAttribute attribute);

    CategoriesAttributeValue findCategoriesAttributeValueById(long id);

    void deleteById(Long id);
}
