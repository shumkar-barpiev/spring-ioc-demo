package org.example.iocdemo;

import org.example.iocdemo.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StoreTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private Store store;

    @Test
    void shouldProcessPayment() {
        store.checkout(100.00);

        verify(paymentService, times(1)).processPayment(100.00);
    }
}