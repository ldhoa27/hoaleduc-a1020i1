package com.backend.airline_tickets_agency_management.model.repository.destination;

import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Scenic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScenicRepository extends JpaRepository<Scenic,Long> {
    @Query(value="select * from scenic where destination_id = :para and flag = 1",nativeQuery=true)
    Iterable<Scenic> findScenicByDestination(@Param("para") Long idDestination);

    @Query(value="SELECT * FROM airline_tickets_agency_management.scenic where flag=1 and destination_id =?1" ,nativeQuery=true)
    List<Scenic> findScenicByDestinationId(Long id);
}
