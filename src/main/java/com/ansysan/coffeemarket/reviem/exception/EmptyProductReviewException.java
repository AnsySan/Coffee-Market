package com.ansysan.coffeemarket.reviem.exception;

public class EmptyProductReviewException extends RuntimeException {
    public EmptyProductReviewException() {
        super("Product's review is empty");
    }
}