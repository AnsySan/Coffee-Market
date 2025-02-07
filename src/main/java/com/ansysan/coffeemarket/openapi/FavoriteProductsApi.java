package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.ListOfFavoriteProducts;
import com.ansysan.coffeemarket.openapi.dto.ListOfFavoriteProductsDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface FavoriteProductsApi {
    ResponseEntity<ListOfFavoriteProductsDto> addListOfFavoriteProducts( ListOfFavoriteProducts request);

    ResponseEntity<ListOfFavoriteProductsDto> getListOfFavoriteProducts();

    ResponseEntity<Void> removeProductFromFavorite( UUID productId);
}
