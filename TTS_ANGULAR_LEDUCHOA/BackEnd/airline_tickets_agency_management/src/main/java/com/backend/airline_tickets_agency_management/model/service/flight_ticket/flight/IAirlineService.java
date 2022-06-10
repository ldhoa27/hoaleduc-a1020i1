package com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;

import java.util.Optional;

public interface IAirlineService {
    Iterable<Airline> findAllAirline();
    Optional<Airline> findAirlineById(Long id);
    void saveAirline(Airline airline);
    void removeAirline(Long id);
}
