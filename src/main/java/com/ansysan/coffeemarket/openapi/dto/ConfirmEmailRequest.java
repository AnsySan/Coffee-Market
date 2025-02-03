package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class ConfirmEmailRequest {
    private String Token;
    private String Code;

    public ConfirmEmailRequest(String code) {
    }
}