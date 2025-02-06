package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductInfoDto {
    private UUID Id;
    private String ProductFileUrl;
}
