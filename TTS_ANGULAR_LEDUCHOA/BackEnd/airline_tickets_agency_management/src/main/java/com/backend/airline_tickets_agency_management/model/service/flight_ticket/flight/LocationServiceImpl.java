package com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    private ILocationRepository iLocationRepository;
    @Override
    public Iterable<Location> findAllLocation() {
        return iLocationRepository.findAll();
    }

    @Override
    public Optional<Location> findLocationById(Long id) {
        return iLocationRepository.findById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        return iLocationRepository.save(location);
    }

    @Override
    public Optional<Location> findLocationByCityName(String name) {
        return iLocationRepository.findLocationByCityName(name);
    }

    @Override
    public void removeLocation(Long id) {
        this.iLocationRepository.deleteById(id);
    }

}
