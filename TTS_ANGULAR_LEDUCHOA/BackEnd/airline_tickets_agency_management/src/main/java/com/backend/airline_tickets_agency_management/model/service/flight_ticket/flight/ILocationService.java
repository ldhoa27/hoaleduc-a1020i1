package com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;

import java.util.Optional;

public interface ILocationService {
    Iterable<Location> findAllLocation();
    Optional<Location> findLocationById(Long id);
    Location saveLocation(Location location);
    void removeLocation(Long id);
    Optional<Location> findLocationByCityName(String name);
}
