package com.vusatui.ticket.booking;

import com.vusatui.ticket.booking.facade.BookingFacadeImpl;
import com.vusatui.ticket.booking.service.EventService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookingFacadeImpl bookingFacadeImpl = context.getBean(BookingFacadeImpl.class);
        EventService eventService = context.getBean(EventService.class);
        System.out.printf("%s", bookingFacadeImpl.getUserById(1L));
    }
}
