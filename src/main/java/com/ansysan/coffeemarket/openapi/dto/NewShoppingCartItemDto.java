package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NewShoppingCartItemDto {
    private UUID ProductId;
    private Integer ProductQuantity;
}
