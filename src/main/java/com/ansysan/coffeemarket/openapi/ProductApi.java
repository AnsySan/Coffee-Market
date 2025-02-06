package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductApi {
    ResponseEntity<ProductInfoDto> getProductById(@PathVariable UUID productId);

    ResponseEntity<ProductListWithPaginationInfoDto> getProducts(Integer pageNumber, Integer pageSize, String sortAttribute, String sortDirection, BigDecimal minPrice, BigDecimal maxPrice, Integer minimumAverageRating, List<String> brandNames, List<String> sellersNames);

    ResponseEntity<List<ProductInfoDto>> getProductsByIds( ProductIdsDto productIdsDto);

    ResponseEntity<SellersDto> getAllSellers();

    ResponseEntity<BrandsDto> getAllBrands();
}
