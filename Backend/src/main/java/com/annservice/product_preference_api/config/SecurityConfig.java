package com.annservice.product_preference_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // 關閉 CSRF
            .formLogin(form -> form.disable()) // <== 禁用預設表單登入
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()                   // 所有請求皆允許
            )
            .headers(headers -> headers.frameOptions().disable()) // 允許 h2-console iframe
            .build();
    }
}
