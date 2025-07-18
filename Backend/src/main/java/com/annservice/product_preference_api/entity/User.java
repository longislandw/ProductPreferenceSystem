package com.annservice.product_preference_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {
    
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "email")
    private String email;

    @Column(name = "account", nullable = false, unique = true)
    private String account;
}
