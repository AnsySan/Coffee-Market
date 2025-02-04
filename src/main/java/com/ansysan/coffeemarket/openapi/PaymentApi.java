package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.PaymentConfirmationEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentApi {
    ResponseEntity<PaymentConfirmationEmail> processRedirectEvent(@RequestParam String sessionId);
}