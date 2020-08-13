package com.chozoi.productservice.app.dtos;

import com.chozoi.productservice.domain.entities.product.State;
import com.chozoi.productservice.domain.entities.rate.Rate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("totalQuantity")
    private long totalQuantity;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("longDescription")
    private String longDescription;

    @JsonProperty("state")
    private State state;

    @JsonProperty("price")
    private long price;

    @JsonProperty("discount")
    private double discount;

    @JsonProperty("questions")
    private List<QuestionDTO> questions;

    @JsonProperty("rates")
    private List<RateDTO> rates;

    @JsonProperty("images")
    private List<ImageDTO> images;

    @JsonProperty("values")
    private List<AttributeValueDTO> valueDTOs;

    @JsonProperty("categories")
    private Long categoriesId;

    @JsonProperty("options")
    private List<Long> options;
}
