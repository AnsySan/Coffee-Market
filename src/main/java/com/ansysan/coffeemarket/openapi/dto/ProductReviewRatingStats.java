package com.ansysan.coffeemarket.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ProductReviewRatingStats {
    private UUID productId;
    private String formattedAvgRating;
    private Integer reviewsCount;
    private RatingMap productRatingMap;

}
