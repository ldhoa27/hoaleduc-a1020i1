package com.backend.airline_tickets_agency_management.model.service.homePage;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.Top10FlightDTO;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.repository.ILocationHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageService implements IHomepageService{
    @Autowired
    ILocationHomeRepository iLocationHomeRepository;

    @Override
    public List<Location> findAll() {
        return iLocationHomeRepository.findAll()    ;
    }

    @Override
    public List<Top10FlightDTO> findTop10CheapestFlight() {
        return iLocationHomeRepository.findTop10CheapestFlight();
    }
}
