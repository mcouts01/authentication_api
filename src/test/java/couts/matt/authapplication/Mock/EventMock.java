package couts.matt.authapplication.Mock;

import couts.matt.authapplication.entity.Event;

public class EventMock {

    public static Event getEventMock() {
        var mockEvent = new Event();
        mockEvent.setTitle("Test");
        mockEvent.setEventID(1);
        mockEvent.setDescription("Test Event");

        return mockEvent;
    }
}
