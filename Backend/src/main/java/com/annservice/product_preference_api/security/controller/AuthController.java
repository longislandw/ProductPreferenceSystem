package com.annservice.product_preference_api.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.annservice.product_preference_api.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // 若前後端分離，允許跨域請求
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // @Autowired
    // private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<String> auth() {
        return ResponseEntity.ok("你已成功取得授權");
    }

    @GetMapping("/genKey/{key}")
    public ResponseEntity<String> generatePassword(@PathVariable String key) {
        return ResponseEntity.ok(userService.genKey(key));
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
    // // 驗證帳號密碼是否正確（會自動做密碼加鹽雜湊比對）
    // User user = userService.authenticate(request.getAccount(),
    // request.getPassword());

    // if (user != null) {
    // String token = jwtUtil.generateToken(user.getUserId());

    // LoginResponseDTO response = new LoginResponseDTO();
    // response.setToken(token);
    // response.setUserId(user.getUserId());
    // response.setName(user.getUserName());

    // return ResponseEntity.ok(response);
    // } else {
    // return ResponseEntity.status(401).body("帳號或密碼錯誤");
    // }
    // }
}
