package com.ansysan.coffeemarket.reviem.exception;

import com.ansysan.coffeemarket.common.exception.ResourceNotFoundException;

import java.util.UUID;

public class ProductIdsAreNotMatchException extends ResourceNotFoundException {

    public ProductIdsAreNotMatchException(UUID productId) {
        super(String.format("Product with productId = '%s' doesn't match with the product which product's review was written for. " +
                "Product's review operations (update) are not possible.", productId));
    }
}