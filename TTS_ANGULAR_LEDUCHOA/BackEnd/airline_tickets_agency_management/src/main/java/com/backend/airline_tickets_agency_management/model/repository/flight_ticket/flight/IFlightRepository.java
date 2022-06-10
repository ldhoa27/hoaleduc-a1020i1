package com.backend.airline_tickets_agency_management.model.repository.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IFlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT * FROM flight WHERE flag = 1 order by flight_date,departure_time",nativeQuery = true)
    Page<Flight> findAllFlight(Pageable pageable);
    @Query(value = "SELECT * FROM flight where flag= 1 and flight_code like %?1% " +
            "order by flight_date,departure_time",nativeQuery = true)
    Page<Flight> findByCode(String code, Pageable pageable);
    @Query(value = "SELECT * FROM flight where flag= 1 and flight_price like %?1% " +
            "order by flight_date,departure_time",nativeQuery = true)
    Page<Flight> findByMoney(String code, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update flight set flag = 0 where flight_id = ?1",nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "select * from flight left join airline on flight.airline_id = airline.airline_id " +
            "where airline.airline_name like %?1% " +
            "and flight.flag = 1 " +
            "order by flight_date,departure_time" ,nativeQuery = true )
    Page<Flight> findByAirline(String name, Pageable pageable);

    @Query(value = "SELECT * FROM flight where departure_time like %?1% and flag = 1 " +
            "order by flight_date,departure_time",nativeQuery = true)
    Page<Flight> findByDepartureTime(String time,Pageable pageable);

    @Query(value="select * from flight where flight_id = ?1 and flag = 1 ",nativeQuery =true)
    Flight findFlightById(Long id);

    @Query(value = "SELECT * FROM flight " +
            "WHERE day(flight_date) like %?1% and flag = 1 " +
            "order by flight_date,departure_time",nativeQuery = true)
    Page<Flight> findByDate(String date, Pageable pageable);

}
