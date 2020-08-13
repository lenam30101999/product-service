package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.QuestionDTO;
import com.chozoi.productservice.domain.services.impl.QuestionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private QuestionServices questionService;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(
            @RequestBody QuestionDTO questionDTO, @RequestParam("accessToken") String accessToken) {
        return questionService.addQuestion(questionDTO, accessToken);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id,
                                            @RequestBody QuestionDTO questionDTO, @RequestParam("accessToken") String accessToken) {
        return questionService.updateQuestion(questionDTO, id, accessToken);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return questionService.deleteQuestion(id, accessToken);
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<?> getAllQuestions(@RequestParam("accessToken") String accessToken) {
        return questionService.findAllQuestionsByUserId(accessToken);
    }

}
