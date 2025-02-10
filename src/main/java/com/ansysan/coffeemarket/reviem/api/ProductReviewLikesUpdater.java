package com.ansysan.coffeemarket.reviem.api;

import com.ansysan.coffeemarket.openapi.dto.ProductReviewDto;
import com.ansysan.coffeemarket.reviem.converter.ProductReviewDtoConverter;
import com.ansysan.coffeemarket.reviem.entity.ProductReview;
import com.ansysan.coffeemarket.reviem.entity.ProductReviewLike;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewLikeRepository;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewRepository;
import com.ansysan.coffeemarket.reviem.validator.ProductReviewValidator;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewLikesUpdater {

    private final ProductReviewLikeRepository productReviewLikeRepository;
    private final ProductReviewRepository productReviewRepository;
    private final SecurityPrincipalProvider securityPrincipalProvider;
    private final ProductReviewDtoConverter productReviewDtoConverter;
    private final ProductReviewValidator productReviewValidator;
    private final ProductReviewProvider productReviewProvider;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ProductReviewDto update(final UUID productId,
                                   final UUID productReviewId,
                                   final Boolean newProductReviewLike) {
        var userId = securityPrincipalProvider.getUserId();

        productReviewValidator.validateProductExists(productId);
        productReviewValidator.validateReviewExistsForUser(productReviewId);
        productReviewValidator.validateProductIdIsValid(productId, productReviewId);

        Optional<ProductReviewLike> productReviewLike = productReviewLikeRepository.findByUserIdAndProductReviewId(userId, productReviewId);

        productReviewLike.ifPresentOrElse(
                productReviewLikeEntity -> {
                    if (productReviewLikeEntity.getIsLike().equals(newProductReviewLike)) {
                        productReviewLikeRepository.deleteByProductIdAndProductReviewId(productId, productReviewId);
                    } else {
                        productReviewLikeEntity.setIsLike(newProductReviewLike);
                        productReviewLikeRepository.saveAndFlush(productReviewLikeEntity);
                    }
                },
                () -> {
                    ProductReviewLike newReviewLike = ProductReviewLike.builder()
                            .userId(userId)
                            .productId(productId)
                            .productReviewId(productReviewId)
                            .isLike(newProductReviewLike)
                            .build();
                    productReviewLikeRepository.saveAndFlush(newReviewLike);
                }
        );

        productReviewRepository.updateLikesCount(productReviewId);
        productReviewRepository.updateDislikesCount(productReviewId);

        ProductReview productReview = productReviewProvider.getReviewEntityById(productReviewId);
        return productReviewDtoConverter.toProductReviewDto(productReview);
    }
}