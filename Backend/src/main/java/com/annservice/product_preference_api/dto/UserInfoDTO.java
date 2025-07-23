package com.annservice.product_preference_api.dto;

import java.util.List;

import com.annservice.product_preference_api.entity.UserAuthority;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDTO {
    private String userId;
    private String userName;
    private String email;
    private String account;
    private List<UserAuthority> authorities;
}
