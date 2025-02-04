package com.ansysan.coffeemarket.payment.api;

import com.ansysan.coffeemarket.payment.enums.StripeSessionConstants;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.ansysan.coffeemarket.payment.enums.StripeSessionStatus.SESSION_IS_EXPIRED;


@Slf4j
@RequiredArgsConstructor
@Service(StripeSessionConstants.SESSION_IS_EXPIRED)
public class SessionExpiredScenarioHandler implements SessionScenarioHandler {

    public void handle(Session stripeSession) {
        log.info("{}, id = '{}'.", SESSION_IS_EXPIRED, stripeSession.getId());
    }
}