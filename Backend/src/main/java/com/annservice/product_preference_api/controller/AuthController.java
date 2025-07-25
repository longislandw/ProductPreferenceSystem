package com.annservice.product_preference_api.controller;

import com.annservice.product_preference_api.dto.LoginRequestDTO;
import com.annservice.product_preference_api.dto.LoginResponseDTO;
import com.annservice.product_preference_api.util.JwtUtil;
import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // 若前後端分離，允許跨域請求
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        // 驗證帳號密碼是否正確（會自動做密碼加鹽雜湊比對）
        User user = userService.authenticate(request.getAccount(), request.getPassword());

        if (user != null) {
            String token = jwtUtil.generateToken(user.getUserId());

            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(token);
            response.setUserId(user.getUserId());
            response.setName(user.getUserName());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("帳號或密碼錯誤");
        }
    }
}
