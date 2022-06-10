package com.backend.airline_tickets_agency_management.controller.flight_ticket.flight;



import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.AirlineDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight.IAirlineService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("airline")
public class AirlineRestController {

    @Autowired
    private IAirlineService airlineService;

    @GetMapping(value="/list-airline" )
    public ResponseEntity<List<Airline>> showListAirline() {
        List<Airline> airlineList = (List<Airline>) this.airlineService.findAllAirline();
        if(airlineList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(airlineList,HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Airline> findAirlineById(@PathVariable Long id){
        Optional<Airline> airline = this.airlineService.findAirlineById(id);
        if(!airline.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(airline.get(),HttpStatus.OK);
        }
    }
    @PostMapping(value= "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ObjectError>> createAirline(@Valid @RequestBody AirlineDto airlineDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }else {
            Airline airline =new Airline();
            BeanUtils.copyProperties(airlineDto,airline);
            this.airlineService.saveAirline(airline);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Airline> deleteAirline(@PathVariable Long id){
        Optional<Airline> airline = this.airlineService.findAirlineById(id);
        if(!airline.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            this.airlineService.removeAirline(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
