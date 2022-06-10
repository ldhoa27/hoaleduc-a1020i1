package com.backend.airline_tickets_agency_management.controller.flight_ticket.ticket;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.SearchFlightDto;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.ticket.ISearchFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/search-flight")
public class SearchFlightRestController {
    @Autowired
    private ISearchFlightService searchFlightService;
    @GetMapping()
    public ResponseEntity<List<SearchFlightDto>> searchFlight(@RequestParam String pointOfDeparture, @RequestParam String destination, @RequestParam String flightDate, @RequestParam String passengerType1, @RequestParam String passengerType2, @RequestParam String orderBy ) {
        System.out.println(pointOfDeparture);
        System.out.println(destination);
        System.out.println(flightDate);
        System.out.println(passengerType1);
        System.out.println(passengerType2);
        System.out.println(orderBy);
        List<SearchFlightDto> flights = searchFlightService.searchFlight(pointOfDeparture, destination, flightDate, passengerType1, passengerType2, orderBy);
        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}