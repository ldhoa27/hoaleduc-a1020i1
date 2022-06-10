package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

public interface SearchFlightDto {
    String getFlightId();
    String getFlightCode();
    String getDepartureTime();
    String getEndTime();
    String getPointOfDeparture();
    String getDestination();
    String getFlightDate();
    String getAirlineName();
    String getTicketType();
    String getPassengerType();
    String getPrice();
    String getLogo();
    String getTicketId();
    String getChairName();
    String getListId();
    String getLengthListId();
}