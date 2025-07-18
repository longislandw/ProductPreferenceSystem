package com.annservice.product_preference_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.ProductListResponseDTO;
import com.annservice.product_preference_api.repository.ProductListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductListService {

    private final ProductListRepository productListRepository;

    public List<ProductListResponseDTO> getAllProducts() {
        return productListRepository.getAllProducts();
    }
}