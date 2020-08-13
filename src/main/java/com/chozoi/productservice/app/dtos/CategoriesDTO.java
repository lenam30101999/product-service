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
public class CategoriesDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("level")
    private Long level;

    @JsonProperty("parentId")
    private long parentId;

    @JsonProperty("children")
    private List<CategoriesDTO> children;

    @JsonProperty("attributes")
    private List<AttributeDTO> attributeDTOs;

}
