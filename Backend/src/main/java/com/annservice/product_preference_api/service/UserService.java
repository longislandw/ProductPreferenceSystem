package com.annservice.product_preference_api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.UserInfoDTO;
import com.annservice.product_preference_api.entity.UserInfo;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
        Optional<UserInfo> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            UserInfo user = optional.get();
            return new UserInfoDTO(user.getUserId(), user.getUserName(), user.getEmail(), user.getAccount(),
                    user.getAuthorities());
        } else {
            // 從資料庫查詢「visitor」使用者
            UserInfo visitor = userRepository.findById("0000").orElseThrow(() -> new RuntimeException("找不到權限 VISITOR"));
            return new UserInfoDTO(visitor.getUserId(), visitor.getUserName(), visitor.getEmail(), visitor.getAccount(),
                    visitor.getAuthorities());
        }
    }

    // 取得所有使用者資訊
    public List<UserInfoDTO> getAllUserInfo() {
        List<UserInfo> users = userRepository.findAll();
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
