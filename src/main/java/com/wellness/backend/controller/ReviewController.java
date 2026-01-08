package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Review;
import com.wellness.backend.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review addReview(
            @RequestParam String email,
            @RequestParam Long practitionerId,
            @RequestParam int rating,
            @RequestParam String comment) {

        return reviewService.addReview(email, practitionerId, rating, comment);
    }

    @GetMapping("/{practitionerId}")
    public List<Review> getReviews(@PathVariable Long practitionerId) {
        return reviewService.getReviews(practitionerId);
    }
}
