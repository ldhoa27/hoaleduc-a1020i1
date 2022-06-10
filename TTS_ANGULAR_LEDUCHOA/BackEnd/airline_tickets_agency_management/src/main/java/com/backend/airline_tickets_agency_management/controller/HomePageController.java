package com.backend.airline_tickets_agency_management.controller;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.Top10FlightDTO;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.service.homePage.IHomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/homepage")
public class HomePageController {
    @Autowired
    IHomepageService iHomepageService;

    @GetMapping("all_location")
    public ResponseEntity<List<Location>> getListLocation() {
        List<Location> locationList = iHomepageService.findAll();
        if (locationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(locationList, HttpStatus.OK);
        }
    }

    @GetMapping("top10cheapestFlights")
    public ResponseEntity<List<Top10FlightDTO>> getTop10cheapestFlights() {
        List<Top10FlightDTO> top10FlightDTOList = iHomepageService.findTop10CheapestFlight();
        if (top10FlightDTOList.isEmpty()) {
            return new ResponseEntity <>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(top10FlightDTOList, HttpStatus.OK);
        }
    }
}
