package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    private String token;
    private String refreshToken;
}
