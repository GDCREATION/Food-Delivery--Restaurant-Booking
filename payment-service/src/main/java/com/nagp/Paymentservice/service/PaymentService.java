package com.nagp.Paymentservice.service;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final PaymentClient paymentClient;

    public PaymentService(CircuitBreakerFactory circuitBreakerFactory, PaymentClient paymentClient) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.paymentClient = paymentClient;
    }

    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        return circuitBreakerFactory.create("makePayment").run(
            () -> paymentClient.makePayment(paymentRequest),
            throwable -> handlePaymentFailure(throwable)
        );
    }

    private PaymentResponse handlePaymentFailure(Throwable throwable) {
        // Provide fallback behavior or return a default PaymentResponse
        return new PaymentResponse("Failed", "Payment service is unavailable.");
    }
}

