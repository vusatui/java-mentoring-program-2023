package com.vusatui.ticket.booking.service;

import java.util.Date;
import java.util.List;

import com.vusatui.ticket.booking.dao.EventDAO;
import com.vusatui.ticket.booking.model.Event;

public class EventService {

    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public Event getEventById(long eventId) {
        return null;
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return null;
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return null;
    }

    public Event createEvent(Event event) {
        return null;
    }

    public Event updateEvent(Event event) {
        return null;
    }

    public boolean deleteEvent(long eventId) {
        return false;
    }

}
