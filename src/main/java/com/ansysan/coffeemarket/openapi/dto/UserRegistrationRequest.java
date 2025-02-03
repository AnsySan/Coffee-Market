package com.ansysan.coffeemarket.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
}

