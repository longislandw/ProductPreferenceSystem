package com.annservice.product_preference_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.repository.LikeListRepository;

@Service
public class LikeListService {

    private final LikeListRepository likeListRepository;

    @Autowired
    public LikeListService(LikeListRepository likeListRepository) {
        this.likeListRepository = likeListRepository;
    }

    public List<LikeListResponseDTO> getUserLikeList(String userId) {
        return likeListRepository.findLikeListByUserId(userId);
    }
}