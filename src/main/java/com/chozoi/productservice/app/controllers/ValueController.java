package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.AttributeValueDTO;
import com.chozoi.productservice.domain.services.impl.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/values")
public class ValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping("/add-value")
    public ResponseEntity<?> addValue(@RequestBody AttributeValueDTO valueDTO,
                                      @RequestParam("accessToken") String accessToken){
        return attributeValueService.createAttributeValue(valueDTO, accessToken);
    }

    @PutMapping("/edit-value/{id}")
    public ResponseEntity<?> editValue(@RequestBody AttributeValueDTO valueDTO,
                                       @PathVariable("id") long id,
                                       @RequestParam("accessToken") String accessToken){
        return attributeValueService.udpateAttributeValue(valueDTO, id, accessToken);
    }

    @DeleteMapping("/delete-value/{id}")
    public ResponseEntity<?> deleteValue(@PathVariable("id") long id,
                                         @RequestParam("accessToken") String accessToken){
        return attributeValueService.deleteAttributeValue(id, accessToken);
    }

    @GetMapping("/get-value-by-attribute/{id}")
    public ResponseEntity<?> getValueByAttributeId(@PathVariable("id") long id,
                                                   @RequestParam("accessToken") String accessToken){
        return attributeValueService.findValueByAttributeId(id, accessToken);
    }
}
