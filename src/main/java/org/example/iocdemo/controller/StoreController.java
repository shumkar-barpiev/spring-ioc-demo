package org.example.iocdemo.controller;

import org.example.iocdemo.Store;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final Store store;

    public StoreController(Store store) {
        this.store= store;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam double amount) {
        store.checkout(amount);
        return "Checkout completed for amount: $" + amount;
    }
}
