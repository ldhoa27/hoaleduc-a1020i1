package com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;
import com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight.IAirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineServiceImpl implements IAirlineService {
    @Autowired
    private IAirlineRepository iAirlineRepository;
    @Override
    public Iterable<Airline> findAllAirline() {
        return iAirlineRepository.findAll();
    }

    @Override
    public Optional<Airline> findAirlineById(Long id) {
        return iAirlineRepository.findById(id);
    }

    @Override
    public void saveAirline(Airline airline) {
        this.iAirlineRepository.save(airline);
    }


    @Override
    public void removeAirline(Long id) {
        this.iAirlineRepository.deleteById(id);
    }
}
