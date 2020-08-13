package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.variant.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
