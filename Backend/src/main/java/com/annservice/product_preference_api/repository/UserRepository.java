package com.annservice.product_preference_api.repository;

import com.annservice.product_preference_api.entity.UserInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByAccount(String account);
    // Spring Data JPA 自動根據欄位名稱產生查詢

    Optional<UserInfo> findByUserId(String userId);
    // Spring Data JPA 自動根據欄位名稱產生查詢
}
