package com.backend.airline_tickets_agency_management.model.service.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.SearchFlightDto;

import java.util.List;

public interface ISearchFlightService {
    List<SearchFlightDto> searchFlight(String pointOfDeparture, String destination, String flightDate, String passengerType1, String passengerType2, String orderBy);

}