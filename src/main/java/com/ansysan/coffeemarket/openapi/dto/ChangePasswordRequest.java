package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String Email;
    private String Password;
    String Code;
}