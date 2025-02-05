package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProductQuantityInShoppingCartItemRequest {
    private UUID productId;
    private Integer quantity;


    public UUID getShoppingCartItemId(){
        return productId;
    }

    public Integer getProductQuantityChange(){
        return quantity;
    }
}
