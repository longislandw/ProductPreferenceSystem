package com.annservice.product_preference_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn", nullable = false, unique = true)
    private Long sn;

    @Column(name = "order_name")
    private Integer orderName; // 購買數量

    @Column(name = "account")
    private String account; // 使用者的帳號

    @Column(name = "total_fee")
    private Double totalFee;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key 對應 User 類別的 userId
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "product_no", nullable = false) // foreign key 對應 Product 類別的 productNo
    private Product product;
}
