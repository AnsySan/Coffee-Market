package com.ansysan.coffeemarket.cart.api;

import com.ansysan.coffeemarket.cart.converter.ShoppingCartDtoConverter;
import com.ansysan.coffeemarket.cart.entity.ShoppingCart;
import com.ansysan.coffeemarket.cart.entity.ShoppingCartItem;
import com.ansysan.coffeemarket.cart.repository.ShoppingCartRepository;
import com.ansysan.coffeemarket.openapi.dto.NewShoppingCartItemDto;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartDto;
import com.ansysan.coffeemarket.product.entity.ProductInfo;
import com.ansysan.coffeemarket.product.repository.ProductInfoRepository;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddItemsToShoppingCartHelper {

    public static final int DEFAULT_PRODUCTS_QUANTITY = 0;

    private final ShoppingCartRepository shoppingCartRepository;
    private final SecurityPrincipalProvider securityPrincipalProvider;
    private final ProductInfoRepository productInfoRepository;
    private final ShoppingCartDtoConverter shoppingCartDtoConverter;
    private final ShoppingCartCreator shoppingCartCreator;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ShoppingCartDto add(final Set<NewShoppingCartItemDto> itemsToAdd) {
        UUID userId = securityPrincipalProvider.getUserId();

        ShoppingCart shoppingCart = Optional.ofNullable(shoppingCartRepository.findShoppingCartByUserId(userId))
                .orElseGet(() -> {
                    log.info("The shopping cart was not found.");
                    return shoppingCartCreator.createNewShoppingCart(userId);
                });

        List<ShoppingCartItem> items = createItems(itemsToAdd, shoppingCart);

        ShoppingCart updatedShoppingCart = updateExistingShoppingCart(shoppingCart, items);

        ShoppingCart persistedShoppingCart = shoppingCartRepository.save(updatedShoppingCart);
        return shoppingCartDtoConverter.toDto(persistedShoppingCart);
    }

    private List<ShoppingCartItem> createItems(Set<NewShoppingCartItemDto> itemsToAdd, ShoppingCart shoppingCart) {
        Map<UUID, Integer> productsWithQuantity = itemsToAdd.stream()
                .collect(Collectors.toMap(NewShoppingCartItemDto::getProductId, NewShoppingCartItemDto::getProductQuantity));

        Set<UUID> existingProductIds = shoppingCart.getItems().stream()
                .map(ShoppingCartItem::getProductInfo)
                .map(ProductInfo::getProductId)
                .collect(Collectors.toSet());

        Set<UUID> newProductIds = productsWithQuantity.keySet().stream()
                .filter(productId -> !existingProductIds.contains(productId))
                .collect(Collectors.toSet());

        return productInfoRepository.findAllById(newProductIds).stream()
                .map(productInfo ->
                        ShoppingCartItem.builder()
                                .shoppingCart(shoppingCart)
                                .productQuantity(productsWithQuantity.get(productInfo.getProductId()))
                                .productInfo(productInfo)
                                .build()
                )
                .toList();
    }

    private static ShoppingCart updateExistingShoppingCart(ShoppingCart existingShoppingCart,
                                                           List<ShoppingCartItem> shoppingCartItems) {
        int productsQuantity = shoppingCartItems.stream()
                .map(ShoppingCartItem::getProductQuantity)
                .reduce(Integer::sum)
                .orElse(DEFAULT_PRODUCTS_QUANTITY);

        existingShoppingCart.setItemsQuantity(existingShoppingCart.getItemsQuantity() + shoppingCartItems.size());
        existingShoppingCart.setProductsQuantity(existingShoppingCart.getProductsQuantity() + productsQuantity);
        existingShoppingCart.getItems().addAll(shoppingCartItems);
        return existingShoppingCart;
    }
}