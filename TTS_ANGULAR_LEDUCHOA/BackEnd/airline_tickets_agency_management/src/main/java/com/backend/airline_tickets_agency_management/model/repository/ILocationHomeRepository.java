package com.backend.airline_tickets_agency_management.model.repository;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.Top10FlightDTO;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILocationHomeRepository extends JpaRepository<Location, Long> {
    @Query (value = "select * from Location", nativeQuery = true)
    List<Location> findAll();

    @Query (value = "select fli.flight_id as flightId, fli.destination as locationTo, fli.point_of_departure as LocationFrom, tic.ticket_price as TicketPrice, loc.city_name as LocationToName, loca.city_name as LocationFromName, fli.flight_date as FlightDate " +
            "from flight fli " +
            "join ticket tic on tic.flight_id = fli.flight_id " +
            "join location loc on loc.location_id = fli.destination " +
            "join location loca on loca.location_id = fli.point_of_departure " +
            "group by fli.flight_id " +
            "order by tic.ticket_price " +
            "limit 10;", nativeQuery = true)
    List<Top10FlightDTO> findTop10CheapestFlight();
}
