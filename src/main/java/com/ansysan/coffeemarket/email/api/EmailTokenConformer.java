package com.ansysan.coffeemarket.email.api;

import com.ansysan.coffeemarket.email.token.TokenManager;
import com.ansysan.coffeemarket.openapi.dto.ConfirmEmailRequest;
import com.ansysan.coffeemarket.openapi.dto.UserRegistrationRequest;
import com.ansysan.coffeemarket.openapi.dto.UserRegistrationResponse;
import com.ansysan.coffeemarket.security.api.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTokenConformer {

    private final UserRegistrationService userRegistrationService;
    private final TokenManager tokenManager;

    public UserRegistrationResponse confirmEmailByCode(final ConfirmEmailRequest confirmEmailRequest) {
        UserRegistrationRequest userRegistrationRequest = tokenManager.validateToken(confirmEmailRequest);
        return userRegistrationService.register(userRegistrationRequest);
    }

    public void confirmResetPasswordEmailByCode(final ConfirmEmailRequest confirmEmailRequest) {
        tokenManager.validateToken(confirmEmailRequest);
    }
}