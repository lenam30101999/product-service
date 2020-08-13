package com.chozoi.productservice.domain.entities.questions;

import com.chozoi.productservice.domain.entities.BaseEntity;
import com.chozoi.productservice.domain.entities.answer.Answer;
import com.chozoi.productservice.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private long userId;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
