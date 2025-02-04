package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class ShoppingCartDto {
    private Collection<Object> items;
    private Integer ItemsQuantity;
    private BigDecimal ItemsTotalPrice;
}
