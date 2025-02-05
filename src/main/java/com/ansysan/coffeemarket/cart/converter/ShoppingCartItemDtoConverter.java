package com.ansysan.coffeemarket.cart.converter;

import com.ansysan.coffeemarket.cart.entity.ShoppingCartItem;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartItemDto;
import com.ansysan.coffeemarket.product.converter.ProductInfoDtoConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ProductInfoDtoConverter.class)
public interface ShoppingCartItemDtoConverter {

    @Named("toShoppingCartItemDto")
    @Mapping(target = "productInfo", source = "productInfo", qualifiedByName = {"toProductInfoDto"})
    ShoppingCartItemDto toDto(final ShoppingCartItem entity);
}