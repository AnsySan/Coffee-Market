package com.ansysan.coffeemarket.astartup;

import com.ansysan.coffeemarket.product.entity.ProductInfo;
import com.ansysan.coffeemarket.product.repository.ProductInfoRepository;
import com.ansysan.coffeemarket.reviem.entity.ProductReview;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductsReviewsAndRatingInfoUpdater implements ApplicationRunner {

    private final ProductInfoRepository productInfoRepository;
    private final ProductReviewRepository productReviewRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        for (ProductInfo productInfo : productInfoRepository.findAll()) {
            UUID productId = productInfo.getProductId();
            productInfoRepository.updateAverageRating(productId);
            productInfoRepository.updateReviewsCount(productId);
        }
        for (ProductReview productReview : productReviewRepository.findAll()) {
            UUID productReviewId = productReview.getId();
            productReviewRepository.updateLikesCount(productReviewId);
            productReviewRepository.updateDislikesCount(productReviewId);
        }
    }
}