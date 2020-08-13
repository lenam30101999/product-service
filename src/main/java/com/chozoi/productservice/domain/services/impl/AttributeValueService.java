package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.AttributeValueDTO;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AttributeValueService extends BaseServices {

    public ResponseEntity<?> createAttributeValue(AttributeValueDTO valueDTO, String accessToken) {
        long userId = hasUserToken(accessToken);
        CategoriesAttribute attribute =
                attributeRepository.findCategoriesAttributeById(valueDTO.getAttributeId());

        CategoriesAttributeValue created = CategoriesAttributeValue.builder()
                .value(valueDTO.getValue())
                .attribute(attribute)
                .userId(userId)
                .build();

        created = valueRepository.save(created);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<?> udpateAttributeValue(AttributeValueDTO valueDTO,
                                                  Long id, String accessToken){
        Long userId = hasUserToken(accessToken);

        if (Objects.isNull(userId)){
            throw new NotFoundException("User Not Found!");
        }

        CategoriesAttributeValue updated = valueRepository.findCategoriesAttributeValueById(id);

        if (Objects.nonNull(updated)){
            updated.setValue(valueDTO.getValue());

            valueRepository.saveAndFlush(updated);
            return ResponseEntity.ok("Success");
        }else return ResponseEntity.ok("Not Found!");
    }

    public ResponseEntity<?> deleteAttributeValue(Long id, String accessToken){
        Long userId = hasUserToken(accessToken);

        if (Objects.isNull(userId)){
            throw new NotFoundException("User Not Found!");
        }

        CategoriesAttributeValue deleted = valueRepository.findCategoriesAttributeValueById(id);

        if (Objects.nonNull(deleted)){
            valueRepository.deleteById(deleted.getId());

            return ResponseEntity.ok("Success");
        }else return ResponseEntity.ok("Not Found!");
    }

    public ResponseEntity<?> findValueByAttributeId(Long attributeId, String accessToken){
        Long userId = hasUserToken(accessToken);

        CategoriesAttribute attribute = new CategoriesAttribute();
        attribute.setId(attributeId);

        List<CategoriesAttributeValue> value = valueRepository.findAllByAttribute(attribute);

        if (Objects.nonNull(value)){

            return ResponseEntity.ok(convertValueToDTOs(value));
        }else return ResponseEntity.ok("Not Found!");
    }
}
