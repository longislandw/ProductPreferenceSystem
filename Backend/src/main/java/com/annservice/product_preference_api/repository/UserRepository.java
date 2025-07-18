package com.annservice.product_preference_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.annservice.product_preference_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
