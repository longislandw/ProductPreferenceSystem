package com.annservice.product_preference_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.annservice.product_preference_api.dto.LikeListRequestDTO;
import com.annservice.product_preference_api.dto.LikeListResponseDTO;
import com.annservice.product_preference_api.entity.LikeList;
import com.annservice.product_preference_api.entity.Product;
import com.annservice.product_preference_api.entity.UserInfo;
import com.annservice.product_preference_api.exception.InvalidQueryException;
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

    // 取得喜愛清單(by User)
    public List<LikeListResponseDTO> getUserLikeList(String userId) {
        return likeListRepository.findLikeListByUserId(userId);
    }

    // 新增喜愛項目
    public void addLikeList(LikeListRequestDTO dto) {

        Optional<LikeList> existing = likeListRepository
                .findByUser_UserIdAndProduct_ProductNo(dto.getUserId(), dto.getProductNo());

        // 已有紀錄->加上去
        if (existing.isPresent()) {
            LikeList likeItem = existing.get();
            int quantity = likeItem.getOrderName() + dto.getOrderName();
            Double unitPrice = likeItem.getProduct().getPrice();
            Double feeRate = likeItem.getProduct().getFeeRate();

            likeItem.setOrderName(quantity);
            likeItem.setTotalAmount(unitPrice * quantity * (1 + feeRate));
            likeItem.setTotalFee(unitPrice * quantity * feeRate);
            likeListRepository.save(likeItem);

        } else { // 沒有紀錄->新增
            // 取得 Product 和 User 實體
            UserInfo user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new InvalidQueryException("使用者不存在"));
            Product product = productListRepository.findById(dto.getProductNo())
                    .orElseThrow(() -> new InvalidQueryException("商品不存在"));

            // 計算金額與手續費
            LikeList newLikeItem = new LikeList();
            newLikeItem.setOrderName(dto.getOrderName());
            newLikeItem.setAccount(user.getAccount());
            newLikeItem.setUser(user);
            newLikeItem.setProduct(product);
            newLikeItem.setTotalAmount(product.getPrice() * dto.getOrderName() * (1 + product.getFeeRate()));
            newLikeItem.setTotalFee(product.getPrice() * dto.getOrderName() * product.getFeeRate());

            likeListRepository.save(newLikeItem);
        }
    }

    // 刪除指定的喜愛項目
    @Transactional
    public void deleteByUserIdAndProductNo(String userId, Long productNo) {
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidQueryException("使用者不存在"));

        Product product = productListRepository.findById(productNo)
                .orElseThrow(() -> new InvalidQueryException("產品不存在"));

        likeListRepository.deleteByUserAndProduct(user, product);
    }

    // 更新指定的喜愛項目
    @Transactional
    public void updateLikeList(LikeListRequestDTO dto) {
        Optional<LikeList> existing = likeListRepository
                .findByUser_UserIdAndProduct_ProductNo(dto.getUserId(), dto.getProductNo());

        if (existing.isEmpty()) {
            throw new InvalidQueryException("查無此喜好商品資料");
        }

        LikeList likeItem = existing.get();
        int quantity = dto.getOrderName();
        Double unitPrice = likeItem.getProduct().getPrice();
        Double feeRate = likeItem.getProduct().getFeeRate();
        likeItem.setOrderName(quantity);
        likeItem.setTotalAmount(unitPrice * quantity * (1 + feeRate));
        likeItem.setTotalFee(unitPrice * quantity * feeRate);
        likeListRepository.save(likeItem); // 可省略，JPA 在 @Transactional 內自動儲存
    }
}