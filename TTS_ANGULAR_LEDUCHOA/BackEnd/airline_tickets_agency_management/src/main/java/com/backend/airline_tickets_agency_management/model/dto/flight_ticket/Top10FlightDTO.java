package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

public interface Top10FlightDTO {
    Long getFlightId();
    Long getLocationTo();
    Long getLocationFrom();
    String getTicketPrice();
    String getLocationToName();
    String getLocationFromName();
    String getFlightDate();
}
