package com.ansysan.coffeemarket.reviem.exception;

import com.ansysan.coffeemarket.common.exception.ResourceNotFoundException;

import java.util.UUID;

public class ProductReviewNotFoundException extends ResourceNotFoundException {

    public ProductReviewNotFoundException(UUID productReviewId) {
        super(String.format("Product's review with productReviewId = '%s' was not found", productReviewId));
    }
}