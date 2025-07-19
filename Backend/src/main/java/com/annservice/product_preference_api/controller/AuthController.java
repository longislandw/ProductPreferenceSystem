package com.annservice.product_preference_api.controller;

import com.annservice.product_preference_api.dto.LoginRequestDTO;
import com.annservice.product_preference_api.util.JwtUtil;

import lombok.AllArgsConstructor;

import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        User user = userRepository.findByUserIdAndPassword(request.getAccount(), request.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUserId());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("帳號或密碼錯誤");
        }
    }
}
