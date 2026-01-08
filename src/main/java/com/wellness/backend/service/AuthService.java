package com.wellness.backend.service;

import org.springframework.stereotype.Service;

import com.wellness.backend.dto.LoginDto;
import com.wellness.backend.dto.RegisterDto;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(RegisterDto dto) {

        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // plain password for now
        user.setRole("USER");

        return userRepo.save(user);
    }

    public User login(LoginDto dto) {

        return userRepo.findByEmail(dto.getEmail())
                .filter(u -> u.getPassword().equals(dto.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
