package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class ChangeUserPasswordRequest {
    private String NewPassword;
    private String OldPassword;
}