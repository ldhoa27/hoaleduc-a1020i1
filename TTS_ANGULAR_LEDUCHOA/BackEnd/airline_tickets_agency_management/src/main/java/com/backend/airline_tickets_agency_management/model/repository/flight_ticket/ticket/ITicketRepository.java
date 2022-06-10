package com.backend.airline_tickets_agency_management.model.repository.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.TicketCustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "select * from ticket t " +
            "join flight f on t.flight_id = f.flight_id " +
            "join location l1 on f.destination = l1.location_id " +
            "join location l2 on f.point_of_departure = l2.location_id " +
            "where t.passenger_name like %?1% " +

            "and t.ticket_code like %?2% " +
            "and (l1.city_name like %?3% or l2.city_name like %?3%) " +
            "and f.flight_date like %?4% " +
            "and t.flag = 1", nativeQuery = true)
    Page<Ticket> findAllByFilter(String passengerName, String ticketCode, String cityName, String flightDate, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ticket t set t.flag = 0 where t.ticket_id = ?1", nativeQuery = true)
    void deleteTicket(Long id);

    @Modifying
    @Transactional
    @Query(value = "update ticket set ticket_status_id = 3 where ticket_id = ?", nativeQuery = true)
    void updateTicketCancel(Long id);

    @Modifying
    @Transactional
    @Query(value = "update ticket set ticket_status_id = 2 where ticket_id = ?", nativeQuery = true)
    void updateTicketPaid(Long id);

    @Query(value = "SELECT t.ticket_id as ticketId,t.ticket_code as ticketCode,lTo.city_name as pointOfDeparture, \n" +
            "lFrom.city_name as destination,t.booking_date as bookingDate,f.flight_date as flightDate,\n" +
            "(t.ticket_price+(t.ticket_price*ticket_type_price)+(t.plus_baggage*1000)+(t.ticket_price*t.tax)-(t.ticket_price*passenger_type_price)) as priceSell,\n" +
            "ts.ticket_status_name as ticketStatusName\n" +
            "FROM ticket t\n" +
            "LEFT JOIN flight f on f.flight_id = t.flight_id\n" +
            "LEFT JOIN ticket_status ts on ts.ticket_status_id = t.ticket_status_id\n" +
            "LEFT JOIN location lFrom on f.destination = lFrom.location_id\n" +
            "LEFT JOIN location lTo on f.point_of_departure = lTo.location_id\n" +
            "LEFT JOIN `user` us on us.user_id = t.user_id\n" +
            "WHERE us.user_id = ?1 and t.ticket_status_id = '1'" +
            "LIMIT ?2,5", nativeQuery = true)
    List<TicketCustomerDto> findAllTicketCustomerBook(Long id, Integer index);

    @Query(value = "SELECT t.ticket_id as ticketId,t.ticket_code as ticketCode,lTo.city_name as pointOfDeparture, \n" +
            "            lFrom.city_name as destination,t.booking_date as bookingDate,f.flight_date as flightDate,\n" +
            "            (t.ticket_price+(t.ticket_price*ticket_type_price)+(t.plus_baggage*1000)+(t.ticket_price*t.tax)-(t.ticket_price*passenger_type_price)) as priceSell,\n" +
            "            ts.ticket_status_name as ticketStatusName\n" +
            "            FROM ticket t\n" +
            "            LEFT JOIN flight f on f.flight_id = t.flight_id\n" +
            "            LEFT JOIN ticket_status ts on ts.ticket_status_id = t.ticket_status_id\n" +
            "            LEFT JOIN location lFrom on f.destination = lFrom.location_id\n" +
            "            LEFT JOIN location lTo on f.point_of_departure = lTo.location_id\n" +
            "            LEFT JOIN `user` us on us.user_id = t.user_id\n" +
            "            WHERE us.user_id = ?1\n" +
            "            LIMIT ?2,5", nativeQuery = true)
    List<TicketCustomerDto> findAllTicketCustomerTransaction(Long id, Integer index);

}
