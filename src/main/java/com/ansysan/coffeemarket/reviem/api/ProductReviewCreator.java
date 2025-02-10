package com.ansysan.coffeemarket.reviem.api;

import com.ansysan.coffeemarket.openapi.dto.ProductReviewDto;
import com.ansysan.coffeemarket.openapi.dto.ProductReviewRequest;
import com.ansysan.coffeemarket.product.repository.ProductInfoRepository;
import com.ansysan.coffeemarket.reviem.converter.ProductReviewDtoConverter;
import com.ansysan.coffeemarket.reviem.entity.ProductReview;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewRepository;
import com.ansysan.coffeemarket.reviem.validator.ProductReviewValidator;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import com.ansysan.coffeemarket.user.api.SingleUserProvider;
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
public class ProductReviewCreator {

    private final ProductReviewRepository reviewRepository;
    private final ProductReviewDtoConverter productReviewDtoConverter;
    private final SecurityPrincipalProvider securityPrincipalProvider;
    private final SingleUserProvider singleUserProvider;
    private final ProductReviewValidator productReviewValidator;
    private final ProductInfoRepository productInfoRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ProductReviewDto create(final UUID productId,
                                   final ProductReviewRequest productReviewRequest) {
        var userId = securityPrincipalProvider.getUserId();
        var productReviewText = productReviewRequest.getText();

        productReviewValidator.validateProductExists(productId);
        productReviewValidator.validateReviewText(productReviewText);
        productReviewValidator.validateReviewExistsForUser(userId, productId);

        var productReview = ProductReview.builder()
                .user(singleUserProvider.getUserEntityById(userId))
                .productId(productId)
                .text(productReviewText.trim())
                .productRating(productReviewRequest.getRating())
                .likesCount(0)
                .dislikesCount(0)
                .build();

        reviewRepository.saveAndFlush(productReview);

        productInfoRepository.updateAverageRating(productId);
        productInfoRepository.updateReviewsCount(productId);

        return productReviewDtoConverter.toProductReviewDto(productReview);
    }
}