package com.vusatui.ticket.booking.service;

import java.util.List;

import com.vusatui.ticket.booking.dao.TicketDAO;
import com.vusatui.ticket.booking.model.Event;
import com.vusatui.ticket.booking.model.Ticket;
import com.vusatui.ticket.booking.model.Ticket.Category;
import com.vusatui.ticket.booking.model.User;

public class TicketService {

    private TicketDAO ticketDAO;

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

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
