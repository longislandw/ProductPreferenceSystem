package com.annservice.product_preference_api.controller;

import java.util.List;
import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.service.LikeListService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like-list")
@RequiredArgsConstructor
public class LikeListController {

    private final LikeListService likeListService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<LikeListResponseDTO>> getLikeListByUserId(@PathVariable String userId) {
        List<LikeListResponseDTO> likeList = likeListService.getUserLikeList(userId);
        return ResponseEntity.ok(likeList);
    }
}