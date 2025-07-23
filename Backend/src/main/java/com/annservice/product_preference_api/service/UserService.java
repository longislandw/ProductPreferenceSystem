package com.annservice.product_preference_api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.UserInfoDTO;
import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.entity.UserAuthority;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // // 注入 Spring Security 提供的密碼加密器
    // private BCryptPasswordEncoder passwordEncoder;

    // public User authenticate(String account, String rawPassword) {
    // Optional<User> user_chk = userRepository.findByAccount(account); // 這裡用帳號查詢

    // if (user_chk.isPresent()) {
    // User user = user_chk.get();
    // if (passwordEncoder.matches(rawPassword, user.getPassword()))
    // return user; // 登入成功
    // }

    // return null; // 登入失敗
    // }

    // 取得使用者資訊(by UserId)
    public UserInfoDTO getUserInfoByUserId(String userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            return new UserInfoDTO(user.getUserId(), user.getUserName(), user.getEmail(), user.getAccount(),
                    user.getAuthorities());
        } else {
            return new UserInfoDTO("0000", "訪客", "visitor@email.com", "00000000", Arrays.asList(new UserAuthority()));
        }
    }

    // 取得所有使用者資訊
    public List<UserInfoDTO> getAllUserInfo() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserInfoDTO(
                        user.getUserId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getAccount(),
                        user.getAuthorities()))
                .collect(Collectors.toList());
    }

}
