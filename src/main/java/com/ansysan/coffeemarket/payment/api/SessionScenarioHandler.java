package com.ansysan.coffeemarket.payment.api;

import com.stripe.model.checkout.Session;

public interface SessionScenarioHandler {

    void handle(final Session session);
}