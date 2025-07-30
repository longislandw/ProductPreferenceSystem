package com.annservice.product_preference_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.UserInfoDTO;
import com.annservice.product_preference_api.dto.UserRegisterInfoDTO;
import com.annservice.product_preference_api.entity.UserInfo;
import com.annservice.product_preference_api.entity.UserAuthority;
import com.annservice.product_preference_api.repository.UserAuthorityRepository;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthRepo;

    // 注入 Spring Security 提供的密碼加密器
    private BCryptPasswordEncoder passwordEncoder;

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

    // 註冊使用者
    public String registerUser(UserRegisterInfoDTO dto) {
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

        userRepository.save(newUser);
        return dto.getUserId();
    }

    // 回傳加密後的密碼 -> 僅供測試用
    public String genKey(String key) {
        return passwordEncoder.encode(key);
    }

}
