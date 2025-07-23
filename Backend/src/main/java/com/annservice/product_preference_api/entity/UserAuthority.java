package com.annservice.product_preference_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_authority")
public class UserAuthority {

    @Id
    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    @Column(name = "description")
    private String description;
}