package com.wellness.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellness.backend.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
