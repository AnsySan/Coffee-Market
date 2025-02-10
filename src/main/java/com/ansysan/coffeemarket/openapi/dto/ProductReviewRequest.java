package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class ProductReviewRequest {
    private int productId;
    private String text;
    private Integer rating;
}
