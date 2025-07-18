package com.annservice.product_preference_api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

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
