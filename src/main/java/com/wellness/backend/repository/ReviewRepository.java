package com.wellness.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellness.backend.entity.Review;
import com.wellness.backend.entity.PractitionerProfile;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPractitioner(PractitionerProfile practitioner);
}
