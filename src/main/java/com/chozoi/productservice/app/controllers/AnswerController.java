package com.chozoi.productservice.app.controllers;

import com.chozoi.productservice.app.dtos.AnswerDTO;
import com.chozoi.productservice.domain.services.impl.AnswerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/answer")
public class AnswerController {

    @Autowired
    private AnswerServices answerService;

    @PostMapping("/add")
    public ResponseEntity<?> addAnswer(
            @RequestBody AnswerDTO answerDTO, @RequestParam("accessToken") String accessToken) {
        return answerService.create(answerDTO, accessToken);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAnswer(
            @RequestBody AnswerDTO answerDTO,
            @PathVariable("id") Long id,
            @RequestParam("accessToken") String accessToken) {
        return answerService.update(answerDTO, id, accessToken);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(
            @PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return answerService.delete(accessToken, id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAnswer(
            @PathVariable("id") Long id, @RequestParam("accessToken") String accessToken) {
        return answerService.findAllByQuestionId(accessToken, id);
    }

}
