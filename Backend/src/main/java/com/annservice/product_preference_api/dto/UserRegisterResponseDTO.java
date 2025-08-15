package com.annservice.product_preference_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterResponseDTO {
    private String userId;
    private String userName;
    private String message;
}
