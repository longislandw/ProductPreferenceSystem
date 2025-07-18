package com.annservice.product_preference_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.repository.LikeListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeListService {

    private final LikeListRepository likeListRepository;

    public List<LikeListResponseDTO> getUserLikeList(String userId) {
        return likeListRepository.findLikeListByUserId(userId);
    }
}