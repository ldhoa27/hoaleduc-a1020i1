package com.backend.airline_tickets_agency_management.model.service.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.TicketCustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Ticket;
import com.backend.airline_tickets_agency_management.model.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService extends IGeneralService<Ticket> {
    Page<Ticket> findAllByFilter(String passengerName, String ticketCode, String cityName, String flightDate, Pageable pageable);
    void updateTicketCancel(Long id);
    void updateTicketPaid(Long id);
    List<TicketCustomerDto> findAllTicketCustomerBook(Long id, Integer index);
    List<TicketCustomerDto> findAllTicketCustomerTransaction(Long id,Integer index);
}
