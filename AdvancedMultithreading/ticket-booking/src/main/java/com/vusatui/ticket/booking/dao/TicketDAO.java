package com.vusatui.ticket.booking.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

import com.vusatui.ticket.booking.model.Ticket;
import com.vusatui.ticket.booking.model.Ticket.Category;
import com.vusatui.ticket.booking.model.TicketDTO;

public class TicketDAO {

    private static final Map<Long, Ticket> tickets = new HashMap<>();

    private static final RandomGenerator randomGenerator = new Random();

    public long createTicket(long eventId, long userId, Category category, int place) {
        Ticket ticket = new TicketDTO(randomGenerator.nextLong(), eventId, userId, category, place);
        tickets.put(ticket.getId(), ticket);
        return ticket.getId();
    }

    public long updateTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
        return ticket.getId();
    }

    public Ticket getTicket(long ticketId) {
        return tickets.get(ticketId);
    }

    public void deleteTicket(long ticketId) {
        tickets.remove(ticketId);
    }

}
