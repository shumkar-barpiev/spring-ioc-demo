package org.example.iocdemo;

import org.example.iocdemo.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class Store {
    private final PaymentService paymentService;

    public Store(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void checkout(double amount) {
        System.out.println("Checking out with amount: $" + amount);
        paymentService.processPayment(amount);
    }
}
