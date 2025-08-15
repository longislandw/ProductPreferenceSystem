package com.annservice.product_preference_api.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.UserRegisterInfoDTO;
import com.annservice.product_preference_api.dto.UserRegisterResponseDTO;
import com.annservice.product_preference_api.entity.UserAuthority;
import com.annservice.product_preference_api.entity.UserInfo;
import com.annservice.product_preference_api.repository.UserAuthorityRepository;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final UserAuthorityRepository userAuthRepo;
    // 注入 Spring Security 提供的密碼加密器
    // passwordEncoder實體化於 security.config.SecurityConfig.java
    @Autowired
    @Qualifier("securityPasswodEncoder")
    private final PasswordEncoder passwordEncoder;

    // 註冊使用者
    public UserRegisterResponseDTO registerUser(UserRegisterInfoDTO dto) {
        UserInfo newUser = new UserInfo();
        // 加密密碼
        String encodedPwd = passwordEncoder.encode(dto.getPassword());
        newUser.setUserId(dto.getUserId());
        newUser.setPassword(encodedPwd);
        newUser.setUserName(dto.getUserName());
        newUser.setEmail(dto.getEmail());
        newUser.setAccount(dto.getAccount());

        // 從資料庫查詢「user」權限
        UserAuthority userRole = userAuthRepo.findById("USER")
                .orElseThrow(() -> new RuntimeException("找不到權限 USER"));
        // 設定權限(list)
        newUser.setAuthorities(new ArrayList<UserAuthority>(List.of(userRole)));

        userRepo.save(newUser);
        UserRegisterResponseDTO response = new UserRegisterResponseDTO(newUser.getUserId(), newUser.getUserName(), "Successfully registered");
        return response;
    }

    // 回傳加密後的密碼 -> 僅供測試用
    public String genKey(String key) {
        return passwordEncoder.encode(key);
    }
}
