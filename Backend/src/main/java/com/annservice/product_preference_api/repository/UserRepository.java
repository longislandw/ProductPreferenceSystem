package com.annservice.product_preference_api.repository;

import com.annservice.product_preference_api.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAccount(String account);
    // Spring Data JPA 自動根據欄位名稱產生查詢

    Optional<User> findByUserId(String userId);
    // Spring Data JPA 自動根據欄位名稱產生查詢
}
