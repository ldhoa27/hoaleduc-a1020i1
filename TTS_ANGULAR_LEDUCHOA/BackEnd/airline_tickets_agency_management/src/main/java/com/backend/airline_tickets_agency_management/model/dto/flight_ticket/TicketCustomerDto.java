package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

public interface TicketCustomerDto {
    String getTicketId();
    String getTicketCode();
    String getPointOfDeparture();
    String getDestination();
    String getBookingDate();
    String getFlightDate();
    Double getPriceSell();
    String getTicketStatusName();

}
