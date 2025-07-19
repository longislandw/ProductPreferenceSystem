package com.annservice.product_preference_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired
    private final UserRepository userRepository;

    // 注入 Spring Security 提供的密碼加密器
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User authenticate(String account, String rawPassword) {
        Optional<User> user_chk = userRepository.findByAccount(account); // 這裡用帳號查詢

        if (user_chk.isPresent() ) {
            User user = user_chk.get();
            if(passwordEncoder.matches(rawPassword, user.getPassword())) return user; // 登入成功
        }

        return null; // 登入失敗
    }
}
