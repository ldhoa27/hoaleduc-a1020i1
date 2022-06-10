package com.backend.airline_tickets_agency_management.model.repository.destination;

import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination,Long> {
    @Query(value="SELECT * FROM airline_tickets_agency_management.destinations where destinations.flag =1",nativeQuery=true)
    Page<Destination> findAllDestination(Pageable pageable);
    Destination findByDestinationName(String destinationName);
}
