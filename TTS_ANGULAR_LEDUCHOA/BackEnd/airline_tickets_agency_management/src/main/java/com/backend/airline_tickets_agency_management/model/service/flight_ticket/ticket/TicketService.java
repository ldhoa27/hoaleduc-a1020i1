package com.backend.airline_tickets_agency_management.model.service.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.TicketCustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Ticket;
import com.backend.airline_tickets_agency_management.model.repository.flight_ticket.ticket.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public Page<Ticket> findAllWithKeyWord(Pageable pageable, String keyword) {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAllNormal() {
        return null;
    }

    @Override
    public void save(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }

    @Override
    public void remove(Long id) {
        this.ticketRepository.deleteTicket(id);
    }

    @Override
    public Page<Ticket> findAllByFilter(String passengerName, String ticketCode, String cityName, String flightDate, Pageable pageable) {
        return this.ticketRepository.findAllByFilter(passengerName, ticketCode, cityName, flightDate, pageable);
    }

    @Override
    public void updateTicketCancel(Long id) {
        ticketRepository.updateTicketCancel(id);
    }

    @Override
    public void updateTicketPaid(Long id) {
        ticketRepository.updateTicketPaid(id);
    }

    @Override
    public List<TicketCustomerDto> findAllTicketCustomerBook(Long id, Integer index) {
        return ticketRepository.findAllTicketCustomerBook(id, index);
    }

    @Override
    public List<TicketCustomerDto> findAllTicketCustomerTransaction(Long id, Integer index) {
        return ticketRepository.findAllTicketCustomerTransaction(id,index);
    }

}
