package com.annservice.product_preference_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.annservice.product_preference_api.dto.ProductListResponseDTO;
import com.annservice.product_preference_api.entity.Product;

@Repository
public interface ProductListRepository extends JpaRepository<Product, Long>{
    @Query("SELECT new com.annservice.product_preference_api.dto.ProductListResponseDTO(p.productName, p.price, p.feeRate) FROM Product p")
    List<ProductListResponseDTO> getAllProducts();
}
