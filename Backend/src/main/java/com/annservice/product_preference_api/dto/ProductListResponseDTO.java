package com.annservice.product_preference_api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class ProductListResponseDTO {
    private Long productNo;
    private String productName;
    private Double price;
    private Double feeRate; // 百分比小數 ex: 0.1 表示 10%
}
