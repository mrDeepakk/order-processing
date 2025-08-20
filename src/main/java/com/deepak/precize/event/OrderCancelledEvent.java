package com.deepak.precize.event;

import java.time.Instant;

public class OrderCancelledEvent extends Event {
    private String orderId;
    private String reason;

    public OrderCancelledEvent(String eventId, Instant timestamp, String orderId, String reason) {
        super(eventId, timestamp, "OrderCancelled");
        this.orderId = orderId;
        this.reason = reason;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }
}
