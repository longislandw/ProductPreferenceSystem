package com.annservice.product_preference_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LikeListResponseDTO {
    private String productName;
    private Integer orderQuantity;
    private Double unitPrice;
    private Double totalPrice;
    private Double totalFee;
    
}
