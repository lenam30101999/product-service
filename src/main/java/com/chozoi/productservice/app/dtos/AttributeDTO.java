package com.chozoi.productservice.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("categoriesId")
    private long categories;

    @JsonProperty("value")
    List<AttributeValueDTO> values;
}
