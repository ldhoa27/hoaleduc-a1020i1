package com.backend.airline_tickets_agency_management.model.entity.flight_ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket_status")
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_status_id")
    private Long ticketStatusId;
    private String ticketStatusName;

    @OneToMany(mappedBy = "ticketStatus")
    @JsonIgnore
    private List<Ticket> tickets;

}