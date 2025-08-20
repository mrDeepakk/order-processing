# Order Processing Backend

A simple **event-driven order processing system** implemented in Java using Spring Boot.  
This project simulates the backend of an e-commerce platform by processing various order-related events such as order creation, payment receipt, shipping scheduling, and order cancellation. Observers are notified of significant status changes.

---

## Features

- Event-driven architecture
- Processes events like:
  - OrderCreated
  - PaymentReceived
  - ShippingScheduled
  - OrderCancelled
- Observer pattern implemented:
  - `LoggerObserver` — logs events and status updates
  - `AlertObserver` — prints alerts for critical changes
- Can read events from a JSON file for batch processing
- Supports multiple orders and event types

---

## 🚀 Local Development

To run this project locally, follow the steps below:

### 📦 Clone the Repository

```shell
https://github.com/mrDeepakk/order-processing.git
cd order-processing
```
### Start application

```bash
mvn compile
mvn exec:java
```
## 🤝 Collaboration

Happy coding! 🚀

