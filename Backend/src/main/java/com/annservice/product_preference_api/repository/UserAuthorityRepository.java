package com.annservice.product_preference_api.repository;

import com.annservice.product_preference_api.entity.UserAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {
}
