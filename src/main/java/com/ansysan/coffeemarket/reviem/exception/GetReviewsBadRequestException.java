package com.ansysan.coffeemarket.reviem.exception;

public class GetReviewsBadRequestException extends RuntimeException {

    public GetReviewsBadRequestException(String errorMessages) {
        super(String.format("GetReviewsRequest parameters are incorrect. Error messages are [ %s ].", errorMessages));
    }
}