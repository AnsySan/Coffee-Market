package com.ansysan.coffeemarket.openai.exception;

public class InappropriateContentException extends RuntimeException {
    public InappropriateContentException(String message) {
        super(message);
    }
}