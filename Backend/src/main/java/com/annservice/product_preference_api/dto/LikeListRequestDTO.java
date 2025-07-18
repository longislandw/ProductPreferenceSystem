package com.annservice.product_preference_api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class LikeListRequestDTO {
    private String userId;
    private Long productNo;
    private Integer orderName;
}