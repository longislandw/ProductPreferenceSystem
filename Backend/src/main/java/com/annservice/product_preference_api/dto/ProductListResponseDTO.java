package com.annservice.product_preference_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductListResponseDTO {
    private String productName;
    private Double price;
    private Double feeRate; // 百分比小數 ex: 0.1 表示 10%
}
