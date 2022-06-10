package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Flight;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AirlineDto {
    private Long airlineId;
    @NotBlank(message = "Not null")
    private String airlineName;
    @NotBlank(message = "Not null")
    private String logo;
    private List<Flight> flights;

    public AirlineDto() {
    }

    public AirlineDto(Long airlineId,  String airlineName, String logo, List<Flight> flights) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.logo = logo;
        this.flights = flights;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
