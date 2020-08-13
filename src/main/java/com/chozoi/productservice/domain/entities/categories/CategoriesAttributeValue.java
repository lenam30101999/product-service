package com.chozoi.productservice.domain.entities.categories;

import com.chozoi.productservice.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CategoriesAttributeValue")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_attribute_id")
    private CategoriesAttribute attribute;

    @ManyToMany(mappedBy = "values")
    private List<Product> products;

}
