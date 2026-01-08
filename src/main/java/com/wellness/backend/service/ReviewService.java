package com.wellness.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.*;
import com.wellness.backend.repository.*;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final PractitionerRepository practitionerRepo;

    public ReviewService(ReviewRepository reviewRepo,
                         UserRepository userRepo,
                         PractitionerRepository practitionerRepo) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.practitionerRepo = practitionerRepo;
    }

    public Review addReview(String email, Long practitionerId, int rating, String comment) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PractitionerProfile practitioner = practitionerRepo.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        Review review = new Review();
        review.setUser(user);
        review.setPractitioner(practitioner);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepo.save(review);
    }

    public List<Review> getReviews(Long practitionerId) {

        PractitionerProfile practitioner = practitionerRepo.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        return reviewRepo.findByPractitioner(practitioner);
    }
}
