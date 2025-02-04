package com.ansysan.coffeemarket.payment.api;

import com.ansysan.coffeemarket.payment.exception.StripeSessionRetrievalException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StripeSessionProvider {

    public Session get(String sessionId) throws StripeSessionRetrievalException {
        Session session;
        try {
            session = Session.retrieve(sessionId);
        } catch (StripeException e) {
            throw new StripeSessionRetrievalException(e.getMessage(), sessionId);
        }
        return session;
    }
}