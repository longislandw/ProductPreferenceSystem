package com.annservice.product_preference_api.controller;

import java.util.List;
import com.annservice.product_preference_api.dto.ProductListResponseDTO;
import com.annservice.product_preference_api.service.ProductListService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductListController {

    private final ProductListService productListService;

    @GetMapping
    public ResponseEntity<List<ProductListResponseDTO>> getAllProducts() {
        List<ProductListResponseDTO> products = productListService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}