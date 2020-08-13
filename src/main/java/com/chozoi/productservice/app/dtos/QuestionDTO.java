package com.chozoi.productservice.app.dtos;

import com.chozoi.productservice.domain.entities.product.Product;
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
public class QuestionDTO {

    @JsonProperty("text")
    private String text;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("productId")
    private long productId;

    @JsonProperty("answers")
    private List<AnswerDTO> answers;
}
