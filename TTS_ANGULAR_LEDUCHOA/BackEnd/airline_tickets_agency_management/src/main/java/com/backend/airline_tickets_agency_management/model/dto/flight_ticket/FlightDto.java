package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Ticket;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class FlightDto implements Validator {
    private Long flightId;
    @NotBlank(message = "Not null")
    private String flightCode;
    @NotBlank(message = "Not null")
    @Pattern(regexp = "^(19|20)?[0-9]{2}[- /.](0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])$")
    private String flightDate;
    @NotBlank(message = "Not null")
    @Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
    private String departureTime;
    @NotBlank(message = "Not null")
    @Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
    private String endTime;
    @NotNull(message = "Not null")
    private Double flightPrice;

    private boolean flag = true;

    @NotNull(message = "Not null")
    private Airline airline;
    private List<Ticket> tickets;
    @NotNull(message = "Not null")
    private Location locationTo;
    @NotNull(message = "Not null")
    private Location locationFrom;

    public FlightDto() {
    }

    public FlightDto(Long flightId,String flightCode, String flightDate,  String departureTime,String endTime, Double flightPrice, Boolean flag,  Airline airline, List<Ticket> tickets,  Location locationTo, Location locationFrom) {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.endTime = endTime;
        this.flightPrice = flightPrice;
        this.flag = flag;
        this.airline = airline;
        this.tickets = tickets;
        this.locationTo = locationTo;
        this.locationFrom = locationFrom;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Location getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(Location locationTo) {
        this.locationTo = locationTo;
    }

    public Location getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(Location locationFrom) {
        this.locationFrom = locationFrom;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FlightDto flightDto = (FlightDto) target;

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        try {
            Date startTime = hourFormat.parse(flightDto.departureTime);
            Date endTime1 = hourFormat.parse(flightDto.endTime);
            if(startTime.after(endTime1)){
                errors.rejectValue("endTime","time.error","giờ đến phải lớn hơn giờ đi");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(flightDto.locationFrom.equals(flightDto.locationTo)) {
            errors.rejectValue("locationFrom","location.error","Điểm đến ko được trùng với điểm đi");
        }



    }
}

