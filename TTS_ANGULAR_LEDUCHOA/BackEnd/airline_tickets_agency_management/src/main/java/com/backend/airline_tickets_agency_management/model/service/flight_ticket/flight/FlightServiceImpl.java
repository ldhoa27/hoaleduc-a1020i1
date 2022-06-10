package com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Flight;
import com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements IFlightService {
    @Autowired
    private IFlightRepository iFlightRepository;

    @Override
    public Page<Flight> findAllFlightPage(Pageable pageable) {
        return iFlightRepository.findAllFlight(pageable);
    }

    @Override
    public void deleteId(Long id) {
        iFlightRepository.deleteById(id);
    }

    @Override
    public Page<Flight> findBycode(String code, Pageable pageable) {
        return this.iFlightRepository.findByCode(code,pageable);
    }

    @Override
    public Page<Flight> findByPrice(String code, Pageable pageable) {
        return this.iFlightRepository.findByMoney(code,pageable);
    }

    @Override
    public Flight findById(Long id) {
        return iFlightRepository.findFlightById(id);
    }

    @Override
    public Page<Flight> findByDepartureTime(String time, Pageable pageable) {
        return iFlightRepository.findByDepartureTime(time,pageable);
    }

    @Override
    public Page<Flight> findByAirline(String name, Pageable pageable) {
        return iFlightRepository.findByAirline(name,pageable);
    }

    @Override
    public Flight findFlightById(Long id) {
        return iFlightRepository.findFlightById(id);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return iFlightRepository.save(flight);
    }
    @Override
    public Page<Flight> findByDate(String date, Pageable pageable) {
        return iFlightRepository.findByDate(date,pageable);
    }

}
