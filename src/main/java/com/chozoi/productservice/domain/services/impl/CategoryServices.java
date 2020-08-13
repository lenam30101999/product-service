package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.CategoriesDTO;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CategoryServices extends BaseServices {

    @SneakyThrows
    public ResponseEntity<?> create(CategoriesDTO categoriesDTO, String accessToken) {
        Categories category = new Categories();

        category.setName(categoriesDTO.getName());
        category.setLevel(categoriesDTO.getLevel());

        if (Objects.nonNull(categoriesDTO.getParentId())){
            Categories parent = categoriesRepository.findCategoriesById(categoriesDTO.getParentId());
            if(parent!=null) {
                category.setParentId(parent);
            }
        }

        String categoryId = String.valueOf(category.getId());
        cacheManager.set(genCategoryIdCacheKey(accessToken), categoryId);

        categoriesRepository.save(category);

        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<?> delete(String accessToken, long id) {
        long categoryId = hasCategoryToken(accessToken);
        Categories deleted = findCategoriesById(categoryId);
        String cacheKey = genCategoryIdCacheKey(accessToken);

        if (Objects.isNull(deleted)) {
            throw new NotFoundException("Not Found!");
        }

        categoriesRepository.deleteById(categoryId);
        cacheManager.delete(cacheKey);

        return ResponseEntity.ok("Deleted!");
    }

    @SneakyThrows
    public ResponseEntity<?> update(CategoriesDTO categoriesDTO, String accessToken, long id) {
        long categoryId = hasCategoryToken(accessToken);
        Categories updated = findCategoriesById(categoryId);

        if (Objects.isNull(updated)) {
            throw new NotFoundException("Not Found Category!");
        }

        updated.setName(categoriesDTO.getName());

        categoriesRepository.saveAndFlush(updated);
        return ResponseEntity.ok("Updated!");
    }

    public ResponseEntity<?> findAllByParentId(Long parentId) {
        List<Categories> categories = categoriesRepository.findAllByParentId(parentId);

        if (Objects.isNull(categories)) {
            throw new NotFoundException("Not Found!");
        }

        return ResponseEntity.ok(convertToCategoriesDTOs(categories));
    }

    public ResponseEntity<?> findAllCategories(){
        List<Categories> categories = categoriesRepository.findCategoryByIdNotNull();

        return ResponseEntity.ok(convertToCategoriesDTOs(categories));
    }

}
