package com.deepak.precize;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.deepak.precize.event.*;
import com.deepak.precize.model.Item;
import com.deepak.precize.observer.AlertObserver;
import com.deepak.precize.observer.LoggerObserver;
import com.deepak.precize.processor.EventProcessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

	public static void main(String[] args) {

		LoggerObserver logger = new LoggerObserver();
		AlertObserver alert = new AlertObserver();

		EventProcessor processor = new EventProcessor(logger, alert);

		List<Item> items = Arrays.asList(new Item("P001", 2));
		OrderCreatedEvent orderCreated = new OrderCreatedEvent("e1", Instant.now(), "ORD001", "CUST001", items, 100.0);
		PaymentReceivedEvent payment = new PaymentReceivedEvent("e2", Instant.now(), "ORD001", 100.0);
		ShippingScheduledEvent shipping = new ShippingScheduledEvent("e3", Instant.now(), "ORD001",
				LocalDate.now().plusDays(3));
		OrderCancelledEvent cancelled = new OrderCancelledEvent("e4", Instant.now(), "ORD002", "Customer request");

		processor.processEvent(orderCreated);
		processor.processEvent(payment);
		processor.processEvent(shipping);
		processor.processEvent(cancelled);

		readEventsFromFile("events.txt", processor);
	}

	public static void readEventsFromFile(String filename, EventProcessor processor) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<String> lines = java.nio.file.Files.readAllLines(new File(filename).toPath());
			for (String line : lines) {
				JsonNode node = mapper.readTree(line);
				String type = node.get("eventType").asText();
				String eventId = node.get("eventId").asText();
				Instant timestamp = Instant.parse(node.get("timestamp").asText());

				switch (type) {
					case "OrderCreated":
						String orderId = node.get("orderId").asText();
						String customerId = node.get("customerId").asText();
						double totalAmount = node.get("totalAmount").asDouble();
						List<Item> items = Arrays.asList(new Item(node.get("items").get(0).get("itemId").asText(),
								node.get("items").get(0).get("qty").asInt()));
						processor.processEvent(
								new OrderCreatedEvent(eventId, timestamp, orderId, customerId, items, totalAmount));
						break;
					case "PaymentReceived":
						processor.processEvent(new PaymentReceivedEvent(eventId, timestamp,
								node.get("orderId").asText(), node.get("amountPaid").asDouble()));
						break;
					case "ShippingScheduled":
						processor.processEvent(new ShippingScheduledEvent(eventId, timestamp,
								node.get("orderId").asText(), LocalDate.parse(node.get("shippingDate").asText())));
						break;
					case "OrderCancelled":
						processor.processEvent(new OrderCancelledEvent(eventId, timestamp,
								node.get("orderId").asText(), node.get("reason").asText()));
						break;
					default:
						System.out.println("Unknown event type: " + type);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
