package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Data
public class ShoppingCartDto {
    private UUID id;
    private Integer UserId;
    private Collection<ShoppingCartItemDto> items;
    private Integer ItemsQuantity;
    private BigDecimal ItemsTotalPrice;
}
