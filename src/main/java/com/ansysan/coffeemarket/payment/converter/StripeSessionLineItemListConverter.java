package com.ansysan.coffeemarket.payment.converter;

import com.ansysan.coffeemarket.openapi.dto.ShoppingCartItemDto;
import com.stripe.param.checkout.SessionCreateParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = BigDecimal.class)
public interface StripeSessionLineItemListConverter {

    List<SessionCreateParams.LineItem> toLineItems(Collection<Object> shoppingCartItems);

    @Mapping(target = "priceData.unitAmount", source = "productInfo.price", qualifiedByName = "toStripeUnitAmount")
    @Mapping(target = "priceData.currency", constant = "USD")
    @Mapping(target = "priceData.productData.name", source = "productInfo.name")
    @Mapping(target = "quantity", source = "productQuantity")
    SessionCreateParams.LineItem toLineItem(ShoppingCartItemDto shoppingCartItem);

    @Named("toStripeUnitAmount")
    default Long toStripeUnitAmount(final BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(100)).longValue();
    }
}