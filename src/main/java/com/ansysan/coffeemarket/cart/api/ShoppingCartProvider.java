package com.ansysan.coffeemarket.cart.api;

import com.ansysan.coffeemarket.cart.converter.ShoppingCartDtoConverter;
import com.ansysan.coffeemarket.cart.entity.ShoppingCart;
import com.ansysan.coffeemarket.cart.exception.ShoppingCartNotFoundException;
import com.ansysan.coffeemarket.cart.repository.ShoppingCartRepository;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartProvider {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDtoConverter shoppingCartDtoConverter;
    private final ShoppingCartCreator shoppingCartCreator;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ShoppingCartDto getByUserId(final UUID userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId);
        if (shoppingCart == null) {
            log.info("The shopping cart was not found.");
            shoppingCart = shoppingCartCreator.createNewShoppingCart(userId);
        }
        return shoppingCartDtoConverter.toDto(shoppingCart);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ShoppingCartDto getByUserIdOrThrow(final UUID userId) {
        return Optional.ofNullable(shoppingCartRepository.findShoppingCartByUserId(userId))
                .map(shoppingCartDtoConverter::toDto)
                .orElseThrow(() -> {
                    log.warn("Shopping cart for user with id = {} was not found.", userId);
                    return new ShoppingCartNotFoundException(userId);
                });
    }
}