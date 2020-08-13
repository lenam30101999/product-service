package com.chozoi.productservice.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    @JsonProperty("text")
    private String text;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("questionId")
    private long questionId;
}
