package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Question;
import com.wellness.backend.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping
    public Question ask(
            @RequestParam String email,
            @RequestParam String content) {
        return service.askQuestion(email, content);
    }

    @GetMapping
    public List<Question> all() {
        return service.getAllQuestions();
    }
}
