package com.chozoi.productservice.domain.entities.categories;

import com.chozoi.productservice.domain.entities.BaseEntity;
import com.chozoi.productservice.domain.entities.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Categories extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Long level;

    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private Categories parentId;

    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Categories> children = new ArrayList<>();

    @OneToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<CategoriesAttribute> attributes;

}
