package com.annservice.product_preference_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.entity.Product;

@Repository
public interface LikeListRepository extends JpaRepository<Product, Long>{
    @Query("SELECT new com.annservice.product_preference_api.dto.LikeListResponseDTO" +
       "(p.productName, l.orderName, p.price, l.totalAmount, l.totalFee) " +
       "FROM LikeList l " +
       "JOIN l.user u " +
       "JOIN l.product p " +
       "WHERE u.userId = :userId")
    List<LikeListResponseDTO> findLikeListByUserId(@Param("userId") String userId);
}