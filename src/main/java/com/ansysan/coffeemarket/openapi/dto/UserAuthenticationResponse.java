package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class UserAuthenticationResponse {
    private String token;
    private String refreshToken;
}
