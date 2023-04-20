package com.vusatui.ticket.booking.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

import com.vusatui.ticket.booking.model.Event;
import com.vusatui.ticket.booking.model.EventDTO;

public class EventDAO {

    private static final Map<Long, Event> events = new HashMap<>();

    private static final RandomGenerator randomGenerator = new Random();

    public long createEvent(String title, Date date) {
        Event event = new EventDTO(randomGenerator.nextLong(), title, date);
        events.put(event.getId(), event);
        return event.getId();
    }

    public long updateEvent(Event event) {
        events.put(event.getId(), event);
        return event.getId();
    }

    public Event getEvent(long eventId) {
        return events.get(eventId);
    }

    public void deleteEvent(long eventId) {
        events.remove(eventId);
    }

}
