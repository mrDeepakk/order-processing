package com.deepak.precize.observer;

import com.deepak.precize.event.Event;
import com.deepak.precize.event.OrderCancelledEvent;
import com.deepak.precize.event.PaymentReceivedEvent;
import com.deepak.precize.event.ShippingScheduledEvent;

public class AlertObserver implements Observer {

    @Override
    public void onEventProcessed(Event event) {
        // Send alert for events
        switch (event.getEventType()) {
            case "OrderCancelled":
                OrderCancelledEvent cancelled = (OrderCancelledEvent) event;
                System.out.println("[Alert] Sending alert: Order "
                        + cancelled.getOrderId()
                        + " has been CANCELLED! Reason: "
                        + cancelled.getReason());
                break;

            case "ShippingScheduled":
                ShippingScheduledEvent shipped = (ShippingScheduledEvent) event;
                System.out.println("[Alert] Order "
                        + shipped.getOrderId()
                        + " is scheduled to ship on "
                        + shipped.getShippingDate());
                break;

            case "PaymentReceived":
                PaymentReceivedEvent payment = (PaymentReceivedEvent) event;
                System.out.println("[Alert] Payment received for Order "
                        + payment.getOrderId()
                        + ", Amount: " + payment.getAmountPaid());
                break;

            default:
                // Do nothing for other events
        }
    }
}
