package com.deepak.precize.event;

import java.time.Instant;
import java.util.List;

import com.deepak.precize.model.Item;;

public class OrderCreatedEvent extends Event {
    private String orderId;
    private String customerId;
    private List<Item> items;
    private double totalAmount;

    public OrderCreatedEvent(String eventId, Instant timestamp, String orderId,
            String customerId, List<Item> items, double totalAmount) {
        super(eventId, timestamp, "OrderCreated");
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
