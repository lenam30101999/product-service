package com.chozoi.productservice.domain;

import com.chozoi.productservice.app.dtos.*;
import com.chozoi.productservice.domain.entities.answer.Answer;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import com.chozoi.productservice.domain.entities.image.Image;
import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.questions.Question;
import com.chozoi.productservice.domain.entities.rate.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mappings({
            @Mapping(target="questionId", source="question.id")
    })
    AnswerDTO answerToResponse(Answer answer);


    @Mappings({
            @Mapping(target = "parentId", source = "parentId.id"),
            @Mapping(target = "level", source = "level"),
            @Mapping(target = "attributeDTOs", source = "attributes"),
            @Mapping(target = "children", source = "children")
    })
    CategoriesDTO categoriesToResponse(Categories categories);


    @Mappings({
            @Mapping(target = "categories", source = "categories.id"),
            @Mapping(target = "values", source = "values")
    })
    AttributeDTO categoriesAttributeToResponse(CategoriesAttribute categoriesAttribute);


    @Mappings({
            @Mapping(target = "attributeId", source = "attribute.id"),
            @Mapping(target = "attribute", source = "attribute.name")
    })
    AttributeValueDTO attributeValueToResponse(CategoriesAttributeValue categoriesAttributeValue);


    @Mappings({
            @Mapping(target = "images", source = "images"),
            @Mapping(target = "rates", source = "rates"),
            @Mapping(target = "questions", source = "questions"),
            @Mapping(target = "valueDTOs", source = "values"),
            @Mapping(target = "categoriesId", source = "categories.id"),
            @Mapping(target = "options", ignore = true)
    })
    ProductDTO productToResponse(Product product);


    @Mapping(target = "productId", source = "product.id")
    QuestionDTO questionToResponse(Question question);


    @Mapping(target = "productId", source = "product.id")
    RateDTO rateToResponse(Rate rate);


    @Mapping(target = "productId", source = "productId.id")
    ImageDTO imageToResponse(Image image);
}
