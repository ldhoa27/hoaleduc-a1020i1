package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

import java.util.List;

public class TicketSendMailDto {
    List<TicketMailDto> ticketMailDtoList;
    String email;

    public TicketSendMailDto() {
    }

    public List<TicketMailDto> getTicketMailDtoList() {
        return ticketMailDtoList;
    }

    public void setTicketMailDtoList(List<TicketMailDto> ticketMailDtoList) {
        this.ticketMailDtoList = ticketMailDtoList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
