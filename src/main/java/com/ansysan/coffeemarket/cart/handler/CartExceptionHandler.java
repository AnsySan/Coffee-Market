package com.ansysan.coffeemarket.cart.handler;

import com.ansysan.coffeemarket.cart.exception.InvalidItemProductQuantityException;
import com.ansysan.coffeemarket.cart.exception.InvalidShoppingCartIdException;
import com.ansysan.coffeemarket.cart.exception.ShoppingCartItemNotFoundException;
import com.ansysan.coffeemarket.cart.exception.ShoppingCartNotFoundException;
import com.ansysan.coffeemarket.common.dto.ApiErrorResponse;
import com.ansysan.coffeemarket.common.handler.ApiErrorResponseCreator;
import com.ansysan.coffeemarket.common.handler.ErrorDebugMessageCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CartExceptionHandler {

    private final ApiErrorResponseCreator apiErrorResponseCreator;
    private final ErrorDebugMessageCreator errorDebugMessageCreator;

    @ExceptionHandler(InvalidItemProductQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidItemProductQuantityException(final InvalidItemProductQuantityException exception) {
        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(exception, HttpStatus.BAD_REQUEST);

        log.error("Handle invalid item's product quantity exception: failed: message: {}, debugMessage: {}.",
                apiErrorResponse.message(), errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }

    @ExceptionHandler(InvalidShoppingCartIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidShoppingCartIdException(final InvalidShoppingCartIdException exception) {
        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(exception, HttpStatus.BAD_REQUEST);
        log.error("Handle invalid shopping cartId exception: failed: message: {}, debugMessage: {}.",
                apiErrorResponse.message(), errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }

    @ExceptionHandler(ShoppingCartItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleShoppingCartItemNotFoundException(final ShoppingCartItemNotFoundException exception) {
        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(exception, HttpStatus.NOT_FOUND);
        log.error("Handle shopping cart item not found exception: failed: message: {}, debugMessage: {}.",
                apiErrorResponse.message(), errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleShoppingCartNotFoundException(final ShoppingCartNotFoundException exception) {
        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(exception, HttpStatus.NOT_FOUND);
        log.error("Handle shopping cart not found exception: failed: message: {}, debugMessage: {}.",
                apiErrorResponse.message(), errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }
}