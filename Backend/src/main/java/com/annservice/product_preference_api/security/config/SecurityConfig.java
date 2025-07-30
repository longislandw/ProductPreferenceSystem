package com.annservice.product_preference_api.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // passwordEncoder實體化於 security.config.SecurityConfig.java
        @Bean("securityPasswodEncoder")
        public PasswordEncoder passwordEncoder() {
                // DelegatingPasswordEncoder 透過密碼的前綴判斷該使用哪種加密方式, Ex:{noop}, {bcrypt}
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        // // 必須提供 authenticationManager，不然自動配置會失效
        // @Bean
        // public AuthenticationManager
        // authenticationManager(AuthenticationConfiguration config) throws Exception {
        // return config.getAuthenticationManager();
        // }

        // @Bean
        // public InMemoryUserDetailsManager inMemoryUserDetailManager() {
        // UserDetails user1 = User
        // .withUsername("user")
        // .password("{noop}user")
        // .authorities("USER")
        // .build();
        // UserDetails user2 = User
        // .withUsername("user2")
        // .password("{noop}222")
        // .authorities("USER")
        // .build();
        // UserDetails user3 = User
        // .withUsername("user3")
        // .password("{noop}333")
        // .authorities("USER")
        // .build();
        // UserDetails admin = User
        // .withUsername("admin")
        // .password("{noop}pass")
        // .authorities("ADMIN", "USER")
        // .build();
        // return new InMemoryUserDetailsManager(List.of(user1, user2, user3, admin));
        // }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .formLogin(Customizer.withDefaults())
                                // 停用/h2-console/**的 CSRF 保護，以避免出現HTTP 403 錯誤。
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                                // 允許同源的app使用 iframe框架
                                .headers(headers -> headers
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                                // .csrf(csrf -> csrf.disable()) // 關閉 CSRF
                                // .formLogin(form -> form.disable()) // <== 禁用預設表單登入
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/auth/genKey/*").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/h2-console/**").hasAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/user/regist").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/user/all").hasAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("USER")
                                                .requestMatchers(HttpMethod.GET, "/api/like-list/**")
                                                .hasAnyAuthority("USER", "ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/auth/**").hasAuthority("ADMIN")
                                                .anyRequest().authenticated()
                                // .anyRequest().permitAll() // 所有請求皆允許
                                )
                                .build();
        }
}
