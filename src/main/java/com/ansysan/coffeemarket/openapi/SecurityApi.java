package com.ansysan.coffeemarket.openapi;

import com.ansysan.coffeemarket.openapi.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SecurityApi {
    @PostMapping(value = "/confirm")
    ResponseEntity<UserRegistrationResponse> confirmEmail(@RequestBody ConfirmEmailRequest confirmEmailRequest);

    @PostMapping("/authenticate")
    ResponseEntity<UserAuthenticationResponse> authenticate(@RequestBody UserAuthenticationRequest request);

    @PostMapping("/refresh")
    ResponseEntity<UserAuthenticationResponse> refreshToken();

    @PostMapping("/logout")
    ResponseEntity<Void> logout();

    @PostMapping("/password/forgot")
    ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequest request);

    @PostMapping("/password/change")
    ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request);
}
