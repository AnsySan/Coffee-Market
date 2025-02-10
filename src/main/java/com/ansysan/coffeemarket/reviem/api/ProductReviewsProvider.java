package com.ansysan.coffeemarket.reviem.api;

import com.ansysan.coffeemarket.openapi.dto.ProductReviewDto;
import com.ansysan.coffeemarket.openapi.dto.ProductReviewsAndRatingsWithPagination;
import com.ansysan.coffeemarket.reviem.converter.ProductReviewDtoConverter;
import com.ansysan.coffeemarket.reviem.repository.ProductReviewRepository;
import com.ansysan.coffeemarket.reviem.validator.ProductReviewValidator;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ansysan.coffeemarket.reviem.converter.ProductReviewDtoConverter.EMPTY_PRODUCT_REVIEW_RESPONSE;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewsProvider {

    private final ProductReviewRepository reviewRepository;
    private final ProductReviewDtoConverter productReviewDtoConverter;
    private final ProductReviewValidator productReviewValidator;
    private final SecurityPrincipalProvider securityPrincipalProvider;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ProductReviewsAndRatingsWithPagination getProductReviews(final UUID productId,
                                                                    final Pageable pageable,
                                                                    final List<Integer> productRatings) {
        productReviewValidator.validateProductExists(productId);
        Page<ProductReviewDto> responsePage = reviewRepository
                .findAllProductReviews(productId, productRatings, pageable)
                .map(productReviewDtoConverter::toProductReviewDto);
        return productReviewDtoConverter.toProductReviewsAndRatingsWithPagination(responsePage);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ProductReviewDto getProductReviewForUser(final UUID productId) {
        productReviewValidator.validateProductExists(productId);
        var userId = securityPrincipalProvider.getUserId();
        return reviewRepository.findByUserIdAndProductId(userId, productId)
                .map(productReviewDtoConverter::toProductReviewDto)
                .orElse(EMPTY_PRODUCT_REVIEW_RESPONSE);
    }
}