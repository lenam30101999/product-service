package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.rate.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatesRepository extends JpaRepository<Rate, Long> {
    Rate save(Rate rate);

    Optional<Rate> findById(Long id);

    Rate findByIdAndUserId(long id, long userId);

    List<Rate> findByUserId(Long id);

    void deleteById(Long id);
}
