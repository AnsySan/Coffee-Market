package com.ansysan.coffeemarket.email.exception;

public class IncorrectTokenFormatException extends RuntimeException{
    public IncorrectTokenFormatException(final String tokenFormat) {
        super("Incorrect token format, token must be " + tokenFormat);
    }
}