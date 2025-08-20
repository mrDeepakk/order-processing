package com.deepak.precize.event;

import java.time.Instant;

public class PaymentReceivedEvent extends Event {
    private String orderId;
    private double amountPaid;

    public PaymentReceivedEvent(String eventId, Instant timestamp, String orderId, double amountPaid) {
        super(eventId, timestamp, "PaymentReceived");
        this.orderId = orderId;
        this.amountPaid = amountPaid;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
