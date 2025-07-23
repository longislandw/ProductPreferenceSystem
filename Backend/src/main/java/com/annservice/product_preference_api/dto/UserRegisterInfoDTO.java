package com.annservice.product_preference_api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterInfoDTO {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String account;
}
