package com.chozoi.productservice.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValueDTO {

    private String attribute;

    private String value;

    private long attributeId;

}
