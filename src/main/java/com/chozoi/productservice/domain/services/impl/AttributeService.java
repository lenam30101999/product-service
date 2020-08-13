package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.AttributeDTO;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AttributeService extends BaseServices {

    public ResponseEntity<?> createAttribute(AttributeDTO attributeDTO, String accessToken) {
        long userId = hasUserToken(accessToken);
        Categories categories =
                categoriesRepository.findCategoriesById(attributeDTO.getCategories());

        CategoriesAttribute created = CategoriesAttribute.builder()
                .name(attributeDTO.getName())
                .userId(userId)
                .categories(categories)
                .build();

        created = attributeRepository.save(created);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<?> udpateAttribute(AttributeDTO attributeDTO,
                                                  Long id, String accessToken){
        Long userId = hasUserToken(accessToken);

        if (Objects.isNull(userId)){
            throw new NotFoundException("User Not Found!");
        }

        CategoriesAttribute updated = attributeRepository.findCategoriesAttributeById(id);

        if (Objects.nonNull(updated)){
            updated.setName(attributeDTO.getName());

            attributeRepository.saveAndFlush(updated);
            return ResponseEntity.ok("Success");
        }else return ResponseEntity.ok("Not Found!");
    }

    public ResponseEntity<?> deleteAttributeValue(Long id, String accessToken){
        Long userId = hasUserToken(accessToken);

        if (Objects.isNull(userId)){
            throw new NotFoundException("User Not Found!");
        }

        CategoriesAttribute deleted = attributeRepository.findCategoriesAttributeById(id);

        if (Objects.nonNull(deleted)){
            attributeRepository.deleteById(deleted.getId());

            return ResponseEntity.ok("Deleted!");
        }else return ResponseEntity.ok("Not Found!");
    }

    public ResponseEntity<?> findValueByCategoriesId(Long categoriesId, String accessToken){
        Long userId = hasUserToken(accessToken);

        Categories categories = new Categories();
        categories.setId(categoriesId);

        if (Objects.isNull(userId)){
            throw new NotFoundException("User Not Found!");
        }

        List<CategoriesAttribute> value = attributeRepository.findAllByCategories(categories);

        if (Objects.nonNull(value)){

            return ResponseEntity.ok(convertAttributeToDTOs(value));
        }else return ResponseEntity.ok("Not Found!");
    }
}
