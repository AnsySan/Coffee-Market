package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.AddNewItemsToShoppingCartRequest;
import com.ansysan.coffeemarket.openapi.dto.DeleteItemsFromShoppingCartRequest;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartDto;
import com.ansysan.coffeemarket.openapi.dto.UpdateProductQuantityInShoppingCartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ShoppingCartApi {
    ResponseEntity<ShoppingCartDto> addNewItemToShoppingCart(AddNewItemsToShoppingCartRequest request);

    ResponseEntity<ShoppingCartDto> getShoppingCart();

    ResponseEntity<ShoppingCartDto> updateProductQuantityInShoppingCartItem(UpdateProductQuantityInShoppingCartItemRequest request);

    ResponseEntity<ShoppingCartDto> deleteItemsFromShoppingCart(DeleteItemsFromShoppingCartRequest request);
}