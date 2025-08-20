package com.deepak.precize.event;

import java.time.Instant;
import java.time.LocalDate;

public class ShippingScheduledEvent extends Event {
    private String orderId;
    private LocalDate shippingDate;

    public ShippingScheduledEvent(String eventId, Instant timestamp, String orderId, LocalDate shippingDate) {
        super(eventId, timestamp, "ShippingScheduled");
        this.orderId = orderId;
        this.shippingDate = shippingDate;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
