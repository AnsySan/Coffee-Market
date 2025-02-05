package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AddNewItemsToShoppingCartRequest {
    private Set<NewShoppingCartItemDto> Items;
}
