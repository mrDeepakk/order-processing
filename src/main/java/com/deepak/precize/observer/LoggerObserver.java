package com.deepak.precize.observer;

import com.deepak.precize.event.Event;

public class LoggerObserver implements Observer {

    @Override
    public void onEventProcessed(Event event) {
        System.out.println("[Logger] Event processed: "
                + event.getEventType()
                + ", Event ID: " + event.getEventId()
                + ", Timestamp: " + event.getTimestamp());
    }
}
