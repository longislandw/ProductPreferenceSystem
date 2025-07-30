package com.annservice.product_preference_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.entity.LikeList;
import com.annservice.product_preference_api.entity.Product;
import com.annservice.product_preference_api.entity.UserInfo;

@Repository
public interface LikeListRepository extends JpaRepository<LikeList, Long> {
    @Query("SELECT new com.annservice.product_preference_api.dto.LikeListResponseDTO" +
            "(p.productNo, p.productName, l.orderName, p.price, l.totalAmount, l.totalFee) " +
            "FROM LikeList l " +
            "JOIN l.user u " +
            "JOIN l.product p " +
            "WHERE u.userId = :userId")
    List<LikeListResponseDTO> findLikeListByUserId(@Param("userId") String userId);
    // 利用 jpa的 prepare statement 進行查詢可避免 sql injection的風險
    // @Param("userId") String userId -> 將 userID 作為查詢字串 input
    // new com.annservice.product_preference_api.dto.LikeListResponseDTO ->
    // 每一列查詢結果產生為一個新的DTO物件, 然後加入LIST回傳

    Optional<LikeList> findByUser_UserIdAndProduct_ProductNo(String userId, Long productNo);
    // Spring Data JPA 自動根據欄位名稱產生查詢

    void deleteByUserAndProduct(UserInfo user, Product product);
}