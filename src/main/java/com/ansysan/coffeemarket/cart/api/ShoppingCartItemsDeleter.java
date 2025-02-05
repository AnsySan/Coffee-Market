package com.ansysan.coffeemarket.cart.api;

import com.ansysan.coffeemarket.cart.repository.ShoppingCartItemRepository;
import com.ansysan.coffeemarket.openapi.dto.DeleteItemsFromShoppingCartRequest;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartDto;
import com.ansysan.coffeemarket.openapi.dto.UserDto;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartItemsDeleter {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ShoppingCartProvider shoppingCartProvider;
    private final SecurityPrincipalProvider securityPrincipalProvider;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ShoppingCartDto delete(final DeleteItemsFromShoppingCartRequest request) {
        List<UUID> itemIds = request.getShoppingCartItemIds();
        shoppingCartItemRepository.deleteAllByIdInBatch(itemIds);

        UserDto userDto = securityPrincipalProvider.get();
        UUID userId = userDto.getId();

        return shoppingCartProvider.getByUserId(userId);
    }
}