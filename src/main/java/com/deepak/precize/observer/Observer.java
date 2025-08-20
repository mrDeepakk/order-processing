package com.deepak.precize.observer;

import com.deepak.precize.event.Event;

public interface Observer {
    void onEventProcessed(Event event);
}
