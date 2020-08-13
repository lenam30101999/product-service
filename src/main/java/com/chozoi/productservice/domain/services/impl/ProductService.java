package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.ProductDTO;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.product.State;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductService extends BaseServices {

    public ResponseEntity<?> create(ProductDTO productDTO, String accessToken) {
        Long userId = hasUserToken(accessToken);

        Categories categories = categoriesRepository.getOne(productDTO.getCategoriesId());
        if (Objects.isNull(categories)) {
            throw new NotFoundException("Category Not Found!");
        }

        List<CategoriesAttributeValue> values = new ArrayList<>();

        for (Long id : productDTO.getOptions()){
            CategoriesAttributeValue value = new CategoriesAttributeValue();
            value.setId(id);
            values.add(value);
        }

        Product product = Product.builder()
                .name(productDTO.getName())
                .userId(userId)
                .totalQuantity(productDTO.getTotalQuantity())
                .shortDescription(productDTO.getShortDescription())
                .longDescription(productDTO.getLongDescription())
                .state(productDTO.getState())
                .price(productDTO.getPrice())
                .discount(productDTO.getDiscount())
                .categories(categories)
                .values(values)
                .build();

        productRepository.save(product);

        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<?> update(ProductDTO productDTO, long id, String accessToken) {
        Long userId = hasUserToken(accessToken);
        Product product = productRepository.findByIdAndUserId(id, userId);
        if (Objects.isNull(product)) {
            throw new NotFoundException("Product Not Found!");
        }

        product.setName(productDTO.getName());
        product.setState(productDTO.getState());
        product.setShortDescription(productDTO.getShortDescription());
        product.setLongDescription(productDTO.getLongDescription());
        product.setTotalQuantity(productDTO.getTotalQuantity());
        product.setDiscount(productDTO.getDiscount());

        productRepository.saveAndFlush(product);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(long id, String accessToken) {
        Long userId = hasUserToken(accessToken);
        Product product = productRepository.findByIdAndUserId(id, userId);

        if (Objects.isNull(product)) {
            throw new NotFoundException("Not Found Product!");
        }
        productRepository.delete(product);
        return ResponseEntity.ok("Deleted!");
    }

    public ResponseEntity<?> findById(long id, String accessToken) {
        Long userId = hasUserToken(accessToken);
        Product product = productRepository.findByIdAndUserId(id, userId);

        if (Objects.isNull(product)) {
            throw new NotFoundException("Not Found Product!");
        }
        ProductDTO response = modelMapper.productToResponse(product);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> findAllByCategoriesId(long categoryId) {
        Categories categories = new Categories();
        categories.setId(categoryId);

        List<Product> products = productRepository.findAllByCategories(categories);
        List<ProductDTO> productDTOS = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        for (Product product : products) {
            productDTOS.add(modelMapper.productToResponse(product));
            response.put("totalData", productDTOS);
            response.put("totalRecords", productRepository.count());
        }
        return ResponseEntity.ok(response);
    }

}
