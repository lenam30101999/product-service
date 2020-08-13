package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.CategoriesDTO;
import com.chozoi.productservice.domain.services.impl.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody CategoriesDTO categoryDTO,
            @RequestParam("accessToken") String accessToken) {
        return categoryService.create(categoryDTO, accessToken);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody CategoriesDTO categoryDTO,
                                    @RequestParam("accessToken") String accessToken) {
        return categoryService.update(categoryDTO, accessToken, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return categoryService.delete(accessToken, id);
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<?> lists() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/get-category-parent/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return categoryService.findAllByParentId(id);
    }
}
