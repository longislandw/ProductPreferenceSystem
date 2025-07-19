package com.annservice.product_preference_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.annservice.product_preference_api.filter.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable()) // 關閉 CSRF
            .formLogin(form -> form.disable()) // <== 禁用預設表單登入
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login", "/api/products", "/h2-console/**").permitAll() // 開放登入和 h2-console
                .anyRequest().authenticated() // 其他請求需驗證
            )
            .headers(headers -> headers.frameOptions().disable()) // 允許 h2-console iframe
            .build();
    }
}
