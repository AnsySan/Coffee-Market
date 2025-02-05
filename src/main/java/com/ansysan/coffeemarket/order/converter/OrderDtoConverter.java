package com.ansysan.coffeemarket.order.converter;

import com.ansysan.coffeemarket.openapi.dto.OrderDto;
import com.ansysan.coffeemarket.openapi.dto.OrderStatus;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartItemDto;
import com.ansysan.coffeemarket.order.entity.Order;
import com.ansysan.coffeemarket.order.entity.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.FIELD,
        uses = {OrderStatus.class})
public interface OrderDtoConverter {

    OrderDto toResponseDto(final Order orderEntity);

    @Named("toOrderItemDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", source = "productInfo.id")
    @Mapping(target = "productName", source = "productInfo.name")
    @Mapping(target = "productPrice", source = "productInfo.price")
    @Mapping(target = "productsQuantity", source = "productQuantity")
    OrderItem toOrderItem(ShoppingCartItemDto cartItem);
}