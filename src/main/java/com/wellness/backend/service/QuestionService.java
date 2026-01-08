package com.wellness.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Question;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.QuestionRepository;
import com.wellness.backend.repository.UserRepository;

@Service
public class QuestionService {

    private final QuestionRepository repo;
    private final UserRepository userRepo;

    public QuestionService(QuestionRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public Question askQuestion(String email, String content) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Question q = new Question();
        q.setUser(user);
        q.setContent(content);
        q.setCreatedAt(LocalDateTime.now());

        return repo.save(q);
    }

    public List<Question> getAllQuestions() {
        return repo.findAll();
    }
}
