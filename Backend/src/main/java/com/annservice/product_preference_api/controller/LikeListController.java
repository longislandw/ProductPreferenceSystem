package com.annservice.product_preference_api.controller;

import java.net.URI;
import java.util.List;

import com.annservice.product_preference_api.dto.LikeListRequestDTO;
import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.service.LikeListService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public ResponseEntity<String> addLikeList(@RequestBody LikeListRequestDTO dto) {
        likeListService.addLikeList(dto);
        URI location = URI.create("/api/like-list/" + dto.getUserId());
        return ResponseEntity.created(location).body("新增成功");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLikeList(
            @RequestParam String userId,
            @RequestParam Long productNo) {
        likeListService.deleteByUserIdAndProductNo(userId, productNo);
        return ResponseEntity.noContent().build();
    }
}