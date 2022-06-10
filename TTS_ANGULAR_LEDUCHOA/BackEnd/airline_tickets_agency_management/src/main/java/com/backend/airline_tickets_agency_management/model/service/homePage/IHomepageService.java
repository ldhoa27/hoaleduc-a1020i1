package com.backend.airline_tickets_agency_management.model.service.homePage;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.Top10FlightDTO;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;

import java.util.List;

public interface IHomepageService {
    List<Location> findAll();
    List<Top10FlightDTO> findTop10CheapestFlight();
}
