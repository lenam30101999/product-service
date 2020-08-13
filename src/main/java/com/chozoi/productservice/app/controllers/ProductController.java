package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.ProductDTO;
import com.chozoi.productservice.domain.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestBody ProductDTO productDTO, @RequestParam("accessToken") String accessToken) {
        return productService.create(productDTO, accessToken);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody ProductDTO productDTO, @RequestParam("accessToken") String accessToken) {
        return productService.update(productDTO, id, accessToken);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return productService.delete(id, accessToken);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<?> getAllProduct(@PathVariable("id") Long id,
                                           @RequestParam("accessToken") String accessToken) {
        return productService.findById(id, accessToken);
    }

    @GetMapping("/get-all-by-categories/{id}")
    public ResponseEntity<?> getAllProduct(@PathVariable("id") Long id) {
        return productService.findAllByCategoriesId(id);
    }
}
