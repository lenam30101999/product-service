package com.chozoi.productservice.domain.entities.categories;

import com.chozoi.productservice.domain.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CategoriesAttribute")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesAttribute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    List<CategoriesAttributeValue> values;
}
