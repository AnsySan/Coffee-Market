package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ListOfFavoriteProducts {
    Set<UUID> ProductIds;
}
