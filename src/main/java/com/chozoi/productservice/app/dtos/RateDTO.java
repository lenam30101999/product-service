package com.chozoi.productservice.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateDTO {

    @JsonProperty("ratePoint")
    private double ratePoints;

    @JsonProperty("product")
    private long productId;

    private long userId;

    private String content;

}
