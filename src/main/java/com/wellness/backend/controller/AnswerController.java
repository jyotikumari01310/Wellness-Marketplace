package com.wellness.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Answer;
import com.wellness.backend.service.AnswerService;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @PostMapping
    public Answer answer(
            @RequestParam Long questionId,
            @RequestParam Long practitionerId,
            @RequestParam String content) {

        return service.answerQuestion(questionId, practitionerId, content);
    }
}
