package com.chozoi.productservice.domain.entities.rate;

import com.chozoi.productservice.domain.entities.BaseEntity;
import com.chozoi.productservice.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double ratePoints;

    private String content;

    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
