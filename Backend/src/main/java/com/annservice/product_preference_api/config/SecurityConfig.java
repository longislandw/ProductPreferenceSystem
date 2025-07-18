package com.annservice.product_preference_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 允許H2 Console的frame載入
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            // 允許H2 Console路徑無認證存取
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                // 其他路徑需要認證
                .anyRequest().authenticated()
            )
            // 關閉csrf保護（H2 Console通常用的是POST，csrf會攔截）
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            ).formLogin(withDefaults());

        return http.build();
    }
}
