package com.annservice.product_preference_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeListRequestDTO {
    private Long productNo;
    private String userId;
    private Integer orderName;
}