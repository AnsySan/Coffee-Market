package com.ansysan.coffeemarket.openai.exception;

public class ChatServiceUnavailableException extends RuntimeException {
    public ChatServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
