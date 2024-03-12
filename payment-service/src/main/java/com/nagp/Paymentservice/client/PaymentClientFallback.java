package com.nagp.Paymentservice.client;

import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient {

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        return new PaymentResponse("Failed", "Payment service is unavailable (fallback).");
    }
}

