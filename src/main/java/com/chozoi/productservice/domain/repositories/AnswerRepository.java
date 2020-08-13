package com.chozoi.productservice.domain.repositories;

import com.chozoi.productservice.domain.entities.answer.Answer;
import com.chozoi.productservice.domain.entities.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer save(Answer answer);

    Optional<Answer> findById(Long id);

    Answer findByIdAndUserId(Long id, Long userId);

    List<Answer> findAnswerByQuestionAndUserId(Question question, long userId);

    void deleteById(Long id);
}
