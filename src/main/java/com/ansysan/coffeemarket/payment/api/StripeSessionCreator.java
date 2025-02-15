package com.ansysan.coffeemarket.payment.api;

import com.ansysan.coffeemarket.cart.api.ShoppingCartProvider;
import com.ansysan.coffeemarket.openapi.dto.ShoppingCartDto;
import com.ansysan.coffeemarket.openapi.dto.UserDto;
import com.ansysan.coffeemarket.payment.converter.StripeSessionLineItemListConverter;
import com.ansysan.coffeemarket.payment.exception.StripeSessionCreationException;
import com.ansysan.coffeemarket.security.api.SecurityPrincipalProvider;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StripeSessionCreator {

    private static final String RETURN_URI = "/orders?sessionId={CHECKOUT_SESSION_ID}";

    private final SecurityPrincipalProvider securityPrincipalProvider;
    private final StripeShippingOptionsProvider stripeShippingOptionsProvider;
    private final StripeSessionLineItemListConverter stripeSessionLineItemListConverter;
    private final ShoppingCartProvider shoppingCartProvider;

    public Session createSession(final HttpServletRequest request) {
        UserDto userDto = securityPrincipalProvider.get();
        UUID userId = userDto.getId();
        ShoppingCartDto shoppingCartDto = shoppingCartProvider.getByUserId(userId);

        SessionCreateParams stripeSessionCreateParams =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                        .setCustomerEmail(userDto.getEmail())
                        .setReturnUrl(getReturnUrl(request))
                        .addAllLineItem(stripeSessionLineItemListConverter.toLineItems(shoppingCartDto.getItems()))
                        .addAllShippingOption(stripeShippingOptionsProvider.getShippingOptions())
                        .putMetadata("userId", userId.toString())
                        .setAutomaticTax(SessionCreateParams.AutomaticTax.builder().setEnabled(true).build())
                        .build();
        try {
            return Session.create(stripeSessionCreateParams);
        } catch (StripeException exception) {
            throw new StripeSessionCreationException(exception.getMessage());
        }
    }

    private String getReturnUrl(final HttpServletRequest request) {
        var url = UriComponentsBuilder.newInstance()
                .scheme(request.getScheme())
                .host(request.getHeader(HttpHeaders.HOST))
                .path(RETURN_URI)
                .build();
        return url.toUriString();
    }
}