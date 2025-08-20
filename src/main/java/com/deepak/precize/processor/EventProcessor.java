package com.deepak.precize.processor;

import java.util.HashMap;
import java.util.Map;

import com.deepak.precize.event.*;
import com.deepak.precize.model.Order;
import com.deepak.precize.model.OrderStatus;
import com.deepak.precize.observer.Observer;

public class EventProcessor {

    private Map<String, Order> orders; // key: orderId, value: Order object
    private Observer[] observers;

    public EventProcessor(Observer... observers) {
        this.orders = new HashMap<>();
        this.observers = observers;
    }

    public void processEvent(Event event) {
        switch (event.getEventType()) {
            case "OrderCreated":
                handleOrderCreated((OrderCreatedEvent) event);
                break;

            case "PaymentReceived":
                handlePaymentReceived((PaymentReceivedEvent) event);
                break;

            case "ShippingScheduled":
                handleShippingScheduled((ShippingScheduledEvent) event);
                break;

            case "OrderCancelled":
                handleOrderCancelled((OrderCancelledEvent) event);
                break;

            default:
                System.out.println("Warning: Unknown event type: " + event.getEventType());
        }

        // Notify all observers
        for (Observer observer : observers) {
            observer.onEventProcessed(event);
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(event.getOrderId(), event.getCustomerId(), event.getItems(), event.getTotalAmount());
        order.addEvent(event);
        orders.put(order.getOrderId(), order);
        System.out.println("Order created: " + order.getOrderId());
    }

    private void handlePaymentReceived(PaymentReceivedEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order == null)
            return;

        order.addEvent(event);
        double paid = event.getAmountPaid();

        if (paid >= order.getTotalAmount()) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.PARTIALLY_PAID);
        }

        System.out.println("Payment received for order: " + order.getOrderId() + ", Status: " + order.getStatus());
    }

    private void handleShippingScheduled(ShippingScheduledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order == null)
            return;

        order.addEvent(event);
        order.setStatus(OrderStatus.SHIPPED);

        System.out.println("Shipping scheduled for order: " + order.getOrderId() + ", Status: SHIPPED");
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order == null)
            return;

        order.addEvent(event);
        order.setStatus(OrderStatus.CANCELLED);

        System.out.println("Order cancelled: " + order.getOrderId() + ", Reason: " + event.getReason());
    }

    // Getter for orders
    public Map<String, Order> getOrders() {
        return orders;
    }
}
