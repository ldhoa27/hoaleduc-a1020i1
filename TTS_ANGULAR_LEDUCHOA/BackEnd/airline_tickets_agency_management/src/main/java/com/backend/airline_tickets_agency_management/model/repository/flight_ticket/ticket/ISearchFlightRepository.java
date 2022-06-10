package com.backend.airline_tickets_agency_management.model.repository.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.SearchFlightDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISearchFlightRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "call search_flight(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    List<SearchFlightDto> searchFlight(String pointOfDeparture, String destination, String flightDate, String passengerType1, String passengerType2, String orderBy);


}