package com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ILocationRepository extends PagingAndSortingRepository<Location,Long> {
    @Query(value = "SELECT * FROM location WHERE city_name = ?1",nativeQuery = true)
    Optional<Location> findLocationByCityName(String name);
}
