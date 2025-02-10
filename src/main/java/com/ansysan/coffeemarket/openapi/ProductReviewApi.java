package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ProductReviewApi {
    public abstract ResponseEntity<ProductReviewDto> addNewProductReview(UUID productId,ProductReviewRequest productReviewRequest);

    public abstract ResponseEntity<Void> deleteProductReview( UUID productId, UUID productReviewId);

    public abstract ResponseEntity<ProductReviewsAndRatingsWithPagination> getProductReviewsAndRatings(UUID productId,Integer pageNumber,Integer pageSize,String sortAttribute, String sortDirection, List<Integer> productRatings);

    public abstract ResponseEntity<ProductReviewDto> getProductReview(UUID productId);

    public abstract ResponseEntity<ProductReviewRatingStats> getRatingAndReviewStat(UUID productId);

    public abstract ResponseEntity<ProductReviewDto> addProductReviewLike(UUID productId,UUID productReviewId,ProductReviewLikeDto request);
}
