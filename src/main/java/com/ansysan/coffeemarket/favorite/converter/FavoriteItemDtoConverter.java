package com.ansysan.coffeemarket.favorite.converter;

import com.ansysan.coffeemarket.favorite.dto.FavoriteItemDto;
import com.ansysan.coffeemarket.favorite.entity.FavoriteItemEntity;
import com.ansysan.coffeemarket.product.converter.ProductInfoDtoConverter;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ProductInfoDtoConverter.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,  injectionStrategy = InjectionStrategy.FIELD)
public interface FavoriteItemDtoConverter {

    @Mapping(target = "productInfo", source = "productInfo")
    @Mapping(target = "productInfo.dateAdded", source = "productInfo.dateAdded", qualifiedByName = "localToOffsetDate")
    FavoriteItemDto toDto(@Context final ProductInfoDtoConverter productInfoDtoConverter,
                          final FavoriteItemEntity favoriteItemEntity);

}