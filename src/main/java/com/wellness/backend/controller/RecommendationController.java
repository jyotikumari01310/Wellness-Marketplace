package com.wellness.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Recommendation;
import com.wellness.backend.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public Recommendation recommend(
            @RequestParam String email,
            @RequestParam String symptom) {

        return service.recommend(email, symptom);
    }
}
