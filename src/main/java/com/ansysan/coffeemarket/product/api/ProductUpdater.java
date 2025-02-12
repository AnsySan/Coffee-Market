package com.ansysan.coffeemarket.product.api;

import com.ansysan.coffeemarket.openapi.dto.ProductInfoDto;
import com.ansysan.coffeemarket.product.api.filestorage.ProductPictureLinkUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductUpdater {

    private final ProductPictureLinkUpdater productPictureLinkUpdater;

    public ProductInfoDto update(ProductInfoDto productInfoDto) {
        productPictureLinkUpdater.update(productInfoDto);
        return productInfoDto;
    }
}