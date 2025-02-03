package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class ShoppingCartItemDto {
    private Collection<Object> Items;
    private Integer ItemsQyantity;
    BigDecimal ItemsTotalPrice;
}
