package com.wellness.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Recommendation;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.RecommendationRepository;
import com.wellness.backend.repository.UserRepository;

@Service
public class RecommendationService {

    private final RecommendationRepository repo;
    private final UserRepository userRepo;

    public RecommendationService(RecommendationRepository repo,
                                 UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public Recommendation recommend(String email, String symptom) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String therapy;

        if (symptom.toLowerCase().contains("pain")) {
            therapy = "Physiotherapy";
        } else if (symptom.toLowerCase().contains("stress")) {
            therapy = "Yoga";
        } else {
            therapy = "Ayurveda";
        }

        Recommendation r = new Recommendation();
        r.setUser(user);
        r.setSymptom(symptom);
        r.setSuggestedTherapy(therapy);
        r.setSource("AI Engine");
        r.setCreatedAt(LocalDateTime.now());

        return repo.save(r);
    }
}
