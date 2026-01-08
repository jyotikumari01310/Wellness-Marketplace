package com.wellness.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellness.backend.entity.Recommendation;

public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {
}
