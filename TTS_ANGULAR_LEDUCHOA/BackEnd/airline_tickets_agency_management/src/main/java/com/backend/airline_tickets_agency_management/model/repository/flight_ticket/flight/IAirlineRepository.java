package com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAirlineRepository extends PagingAndSortingRepository<Airline, Long> {
}
