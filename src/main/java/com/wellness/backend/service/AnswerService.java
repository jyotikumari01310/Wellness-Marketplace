package com.wellness.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Answer;
import com.wellness.backend.entity.PractitionerProfile;
import com.wellness.backend.entity.Question;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.AnswerRepository;
import com.wellness.backend.repository.PractitionerRepository;
import com.wellness.backend.repository.QuestionRepository;

@Service
public class AnswerService {

    private final AnswerRepository repo;
    private final QuestionRepository questionRepo;
    private final PractitionerRepository practitionerRepo;

    public AnswerService(AnswerRepository repo,
                         QuestionRepository questionRepo,
                         PractitionerRepository practitionerRepo) {
        this.repo = repo;
        this.questionRepo = questionRepo;
        this.practitionerRepo = practitionerRepo;
    }

    public Answer answerQuestion(Long questionId, Long practitionerId, String content) {

        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        PractitionerProfile practitioner = practitionerRepo.findById(practitionerId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found"));

        // 🔹 VERY IMPORTANT
        User practitionerUser = practitioner.getUser();

        Answer a = new Answer();
        a.setQuestion(question);
        a.setUser(practitionerUser);   // ✅ CORRECT
        a.setContent(content);
        a.setCreatedAt(LocalDateTime.now());

        return repo.save(a);
    }
}
