package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.AttributeDTO;
import com.chozoi.productservice.domain.services.impl.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping("/add-attribute")
    public ResponseEntity<?> addAttribute(@RequestBody AttributeDTO attributeDTO,
                                          @RequestParam("accessToken") String accessToken){
        return attributeService.createAttribute(attributeDTO, accessToken);
    }

    @PutMapping("/edit-attribute/{id}")
    public ResponseEntity<?> editAttribute(@RequestBody AttributeDTO attributeDTO,
                                           @PathVariable("id") long id,
                                           @RequestParam("accessToken") String accessToken){
        return attributeService.udpateAttribute(attributeDTO, id, accessToken);
    }

    @DeleteMapping("/delete-attribute/{id}")
    public ResponseEntity<?> deleteAttribute(@PathVariable("id") long id,
                                           @RequestParam("accessToken") String accessToken){
        return attributeService.deleteAttributeValue(id, accessToken);
    }

    @GetMapping("/get-by-categories/{categoriesId}")
    public ResponseEntity<?> getByCategories(@PathVariable("categoriesId") long categoriesId,
                                             @RequestParam("accessToken") String accessToken){
        return attributeService.findValueByCategoriesId(categoriesId, accessToken);
    }
}
