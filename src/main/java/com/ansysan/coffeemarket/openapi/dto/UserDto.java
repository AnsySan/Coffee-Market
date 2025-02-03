package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String Email;
    private String FirstName;
    private String LastName;
    private String Password;
    private String AvatarLink;
}