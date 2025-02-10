package com.ansysan.coffeemarket.reviem.api;

import com.ansysan.coffeemarket.product.repository.ProductInfoRepository;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewRepository;
import com.ansysan.coffeemarket.reviem.validator.ProductReviewValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewDeleter {

    private final ProductReviewRepository reviewRepository;
    private final ProductReviewValidator productReviewValidator;
    private final ProductInfoRepository productInfoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void delete(final UUID productId,
                       final UUID productReviewId) {
        productReviewValidator.validateProductReviewDeletionAllowed(productReviewId);
        productReviewValidator.validateProductExists(productId);

        reviewRepository.deleteById(productReviewId);

        productInfoRepository.updateAverageRating(productId);
        productInfoRepository.updateReviewsCount(productId);
    }
}