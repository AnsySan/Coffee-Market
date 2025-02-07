package com.ansysan.coffeemarket.favorite.dto;

import com.ansysan.coffeemarket.openapi.dto.ProductInfoDto;

import java.util.UUID;

public record FavoriteItemDto(UUID id,
                              ProductInfoDto productInfo) {}