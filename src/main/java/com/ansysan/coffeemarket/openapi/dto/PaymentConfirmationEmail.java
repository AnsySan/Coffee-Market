package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

@Data
public class PaymentConfirmationEmail {
    private String email;

    public void customerEmail(String email) {
    }
}
