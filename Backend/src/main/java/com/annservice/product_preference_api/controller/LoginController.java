package com.annservice.product_preference_api.controller;

import com.annservice.product_preference_api.dto.LoginRequestDTO;
import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.service.UserService;
import com.annservice.product_preference_api.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userService.authenticate(loginRequest.getAccount(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body("帳號或密碼錯誤");
        }
        String token = jwtUtil.generateToken(user.getUserId());
        return ResponseEntity.ok().body(token);
    }
}
