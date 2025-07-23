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

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfoByUserId(@PathVariable String userId) {
        UserInfoDTO userInfo = userService.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfo);
    }

    // @PostMapping
    // public String createUser(@RequestBody User user) {
    // userService.insert(user);
    // return member.getId();
    // }

    @GetMapping("/all")
    public List<UserInfoDTO> getMembers() {
        return userService.getAllUserInfo();
    }

    @GetMapping("/selected-courses")
    public String selectedCourses() {
        return "修課清單";
    }

    @GetMapping("/course-feedback")
    public String courseFeedback() {
        return "課程回饋";
    }

    @GetMapping("/home")
    public String home() {
        return "系統首頁";
    }
}
