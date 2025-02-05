package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DeleteItemsFromShoppingCartRequest {
    private UUID shoppingCartId;
    private List<UUID> itemIds;

    public List<UUID> getShoppingCartItemIds(){
        return itemIds;
    }
}
