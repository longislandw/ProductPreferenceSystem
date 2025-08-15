package com.annservice.product_preference_api.controller;

import java.util.List;
import com.annservice.product_preference_api.dto.UserInfoDTO;
import com.annservice.product_preference_api.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 顯示單一使用者資料(by userId)
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfoByUserId(@PathVariable String userId) {
        UserInfoDTO userInfo = userService.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfo);
    }

    // 顯示所有使用者資料
    @GetMapping("/all")
    public List<UserInfoDTO> getMembers() {
        return userService.getAllUserInfo();
    }
}
