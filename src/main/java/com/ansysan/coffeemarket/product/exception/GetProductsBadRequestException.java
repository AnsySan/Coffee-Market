package com.ansysan.coffeemarket.product.exception;

public class GetProductsBadRequestException extends RuntimeException {

    public GetProductsBadRequestException(String errorMessages) {
        super(String.format("GetProductsRequest parameters are incorrect. Error messages are [ %s ].", errorMessages));
    }
}