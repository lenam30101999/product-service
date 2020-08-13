package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.QuestionDTO;
import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.questions.Question;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class QuestionServices extends BaseServices {

    public ResponseEntity<?> addQuestion(QuestionDTO question, String accessToken) {
        Product product = productRepository.findById(question.getProductId()).orElse(null);

        Question saved = Question.builder()
                        .text(question.getText())
                        .product(product)
                        .userId(question.getUserId())
                        .build();

        questionRepository.save(saved);

        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<?> updateQuestion(QuestionDTO newQuestion, long id, String accessToken) {
        Question updated  = questionRepository.findById(id).orElse(null);

        if (Objects.isNull(updated)) {
            throw new NotFoundException("Question Not Found!");
        }

        updated.setText(newQuestion.getText());
        questionRepository.saveAndFlush(updated);
        return ResponseEntity.ok("Updated!");
    }

    public ResponseEntity<?> deleteQuestion(long id, String accessToken) {
        Question deleted = questionRepository.findById(id).orElse(null);

        if (Objects.isNull(deleted)) {
            throw new NotFoundException("Not Found Question!");
        }

        questionRepository.delete(deleted);
        return ResponseEntity.ok("Deleted!");
    }

    @SneakyThrows
    public ResponseEntity<?> findAllQuestionsByUserId(String accessToken) {
        long userId = hasUserToken(accessToken);
        List<Question> questions = questionRepository.findAllByUserId(userId);

        if (Objects.isNull(questions)) {
            throw new NotFoundException("Not Found Any Question!");
        }

        return ResponseEntity.ok(convertToQuestionsDTOs(questions));
    }
}
