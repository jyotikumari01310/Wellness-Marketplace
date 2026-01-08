package com.wellness.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.dto.LoginDto;
import com.wellness.backend.dto.RegisterDto;
import com.wellness.backend.entity.User;
import com.wellness.backend.security.jwt.JwtUtil;
import com.wellness.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {

        User user = authService.login(dto);
        return jwtUtil.generateToken(user.getEmail());
    }
}
