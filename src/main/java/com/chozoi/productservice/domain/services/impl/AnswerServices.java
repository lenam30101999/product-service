package com.chozoi.productservice.domain.services.impl;

import com.chozoi.productservice.app.dtos.AnswerDTO;
import com.chozoi.productservice.domain.entities.answer.Answer;
import com.chozoi.productservice.domain.entities.questions.Question;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.services.BaseServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class AnswerServices extends BaseServices {

    @SneakyThrows
    public ResponseEntity<?> create(AnswerDTO answer, String accessToken) {
        Question question = questionRepository.findById(answer.getQuestionId()).orElse(new Question());
        long userId = hasUserToken(accessToken);

        Answer saved = Answer.builder()
                        .text(answer.getText())
                        .userId(userId)
                        .question(question)
                        .build();

        saved = answerRepository.save(saved);

        return ResponseEntity.ok("Success!");
    }

    @SneakyThrows
    public ResponseEntity<?> update(AnswerDTO newAnswer, long id, String accessToken) {
        long userId = hasUserToken(accessToken);
        Answer answer = answerRepository.findByIdAndUserId(id, userId);

        if (Objects.isNull(answer)) {
            throw new NotFoundException("Answer Not Found!");
        }

        answer.setText(newAnswer.getText());
        answerRepository.saveAndFlush(answer);

        return ResponseEntity.ok("Updated!");
    }

    public ResponseEntity<?> delete(String accessToken, long id) {
        long userId = hasUserToken(accessToken);
        Answer deleted = answerRepository.findByIdAndUserId(id, userId);

        if (Objects.isNull(deleted)) {
            throw new NotFoundException("Not Found Answer!");
        }

        answerRepository.delete(deleted);
        return ResponseEntity.ok("Deleted!");
    }

    @SneakyThrows
    public ResponseEntity<?> findAllByQuestionId(String accessToken, long questionId) {
        long userId = hasUserToken(accessToken);
        Question question = new Question();
        question.setId(questionId);

        List<Answer> answers = answerRepository.findAnswerByQuestionAndUserId(question, userId);

        List<AnswerDTO> responses = convertToAnswersDTOs(answers);

        return ResponseEntity.ok(responses);
    }
}
