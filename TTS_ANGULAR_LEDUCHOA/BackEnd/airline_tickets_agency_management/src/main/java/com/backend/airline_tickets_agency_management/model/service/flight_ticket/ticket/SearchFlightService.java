package com.backend.airline_tickets_agency_management.model.service.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.SearchFlightDto;
import com.backend.airline_tickets_agency_management.model.repository.flight_ticket.ticket.ISearchFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFlightService implements ISearchFlightService{
    @Autowired
    private ISearchFlightRepository iSearchFlightRepository;

    @Override
    public List<SearchFlightDto> searchFlight(String pointOfDeparture, String destination, String flightDate, String passengerType1, String passengerType2, String orderBy) {
        return iSearchFlightRepository.searchFlight(pointOfDeparture, destination, flightDate, passengerType1, passengerType2, orderBy);
    }
}