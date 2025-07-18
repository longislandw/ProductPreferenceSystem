package com.annservice.product_preference_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.LikeListRequestDTO;
import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.entity.LikeList;
import com.annservice.product_preference_api.entity.Product;
import com.annservice.product_preference_api.entity.User;
import com.annservice.product_preference_api.repository.LikeListRepository;
import com.annservice.product_preference_api.repository.ProductListRepository;
import com.annservice.product_preference_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeListService {

    private final LikeListRepository likeListRepository;
    private final ProductListRepository productListRepository;
    private final UserRepository userRepository;

    public List<LikeListResponseDTO> getUserLikeList(String userId) {
        return likeListRepository.findLikeListByUserId(userId);
    }

    public void addLikeList(LikeListRequestDTO dto) {

        Optional<LikeList> existing = likeListRepository
                .findByUser_UserIdAndProduct_ProductNo(dto.getUserId(), dto.getProductNo());

        if (existing.isPresent()) {
            LikeList likeItem = existing.get();
            likeItem.setOrderName(likeItem.getOrderName() + dto.getOrderName());
            likeItem.setTotalAmount(likeItem.getProduct().getPrice() * likeItem.getOrderName());
            likeItem.setTotalFee(likeItem.getTotalAmount() * likeItem.getProduct().getFeeRate());
            likeListRepository.save(likeItem);
        } else {
            // 取得 Product 和 User 實體
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("找不到使用者"));
            Product product = productListRepository.findById(dto.getProductNo())
                    .orElseThrow(() -> new RuntimeException("找不到商品"));

            // 計算金額與手續費
            LikeList newLikeItem = new LikeList();
            newLikeItem.setUser(user);
            newLikeItem.setProduct(product);
            newLikeItem.setOrderName(dto.getOrderName());
            newLikeItem.setTotalAmount(product.getPrice() * dto.getOrderName());
            newLikeItem.setTotalFee(newLikeItem.getTotalAmount() * product.getFeeRate());

            likeListRepository.save(newLikeItem);
        }
    }
}