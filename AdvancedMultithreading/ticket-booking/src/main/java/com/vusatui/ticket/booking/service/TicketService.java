package com.vusatui.ticket.booking.service;

import java.util.List;

import com.vusatui.ticket.booking.model.Event;
import com.vusatui.ticket.booking.model.Ticket;
import com.vusatui.ticket.booking.model.Ticket.Category;
import com.vusatui.ticket.booking.model.User;

public class TicketService {

    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return null;
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
