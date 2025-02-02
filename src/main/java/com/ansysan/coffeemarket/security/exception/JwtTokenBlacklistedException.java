package com.ansysan.coffeemarket.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenBlacklistedException extends AuthenticationException {

    public JwtTokenBlacklistedException() {
        super("JWT Token is blacklisted");
    }
}