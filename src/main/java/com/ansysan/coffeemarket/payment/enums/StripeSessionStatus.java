package com.ansysan.coffeemarket.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StripeSessionStatus {

    SESSION_IS_EXPIRED(StripeSessionConstants.SESSION_IS_EXPIRED, "Checkout Session has expired."),

    SESSION_IS_COMPLETED(StripeSessionConstants.SESSION_IS_COMPLETED, "Payment has succeeded.");

    private final String status;
    private final String description;
}