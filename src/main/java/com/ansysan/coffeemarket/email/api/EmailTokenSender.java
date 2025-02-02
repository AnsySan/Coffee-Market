package com.ansysan.coffeemarket.email.api;

import com.ansysan.coffeemarket.email.sender.AuthTokenEmailConfirmation;
import com.ansysan.coffeemarket.email.token.TokenManager;
import com.ansysan.coffeemarket.openapi.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTokenSender {

    private final AuthTokenEmailConfirmation emailConfirmation;
    private final TokenManager tokenManager;

    public void sendEmailVerificationCode(final UserRegistrationRequest request) {
        String token = tokenManager.generateToken(request);
        emailConfirmation.sendTemporaryCode(request.getEmail(), token);
    }
}