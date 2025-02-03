package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class UserAuthenticationRequest {
    private String Email;
    private String Password;
}
