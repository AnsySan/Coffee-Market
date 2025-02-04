package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class SessionWithClientSecretDto {
    private String sessionId;
    private String clientSecret;
}
