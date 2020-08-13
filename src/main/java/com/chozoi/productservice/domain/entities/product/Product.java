package com.chozoi.productservice.domain.entities.product;

import com.chozoi.productservice.domain.entities.BaseEntity;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import com.chozoi.productservice.domain.entities.image.Image;
import com.chozoi.productservice.domain.entities.questions.Question;
import com.chozoi.productservice.domain.entities.rate.Rate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long totalQuantity;

    private long price;

    private String shortDescription;

    private String longDescription;

    @Enumerated(EnumType.STRING)
    private State state;

    private double discount;

    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<Image> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Value_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "values_id"))
    private List<CategoriesAttributeValue> values;

}
