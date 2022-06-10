package com.backend.airline_tickets_agency_management.model.entity.flight_ticket;

import com.backend.airline_tickets_agency_management.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;
    private String ticketCode;
    private Boolean plusBaby;
    private Double plusBaggage;
    private String ticketType;
    private Double ticketTypePrice;
    private Double ticketPrice;
    private Double tax;
    private String chairName;
    @Column(columnDefinition = "date")
    private String bookingDate;
    private Integer checkInBaggage;
    private Integer carryOnBaggage;
    private String passengerType;
    private Double passengerTypePrice;
    private String passengerName;
    private String passengerGender;
    private String passengerPhone;
    private String passengerIdCard;
    private String passengerEmail;
    @ManyToOne
    @JoinColumn(name = "flight_id",referencedColumnName = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "ticket_status_id",referencedColumnName ="ticket_status_id" )
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean flag = true;

}