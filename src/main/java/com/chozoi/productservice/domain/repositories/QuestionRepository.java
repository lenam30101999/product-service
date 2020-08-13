package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question save(Question question);

    Optional<Question> findById(Long id);

    List<Question> findAllByUserId(long userId);

    List<Question> findAllByProduct(Product product);

    void deleteById(Long id);
}
