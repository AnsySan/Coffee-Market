package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductIdsDto {
    private List<UUID> ProductIds;
}
