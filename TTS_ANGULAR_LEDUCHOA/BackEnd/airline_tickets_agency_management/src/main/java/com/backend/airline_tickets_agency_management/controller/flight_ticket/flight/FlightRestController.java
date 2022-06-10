package com.backend.airline_tickets_agency_management.controller.flight_ticket.flight;

import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.FlightDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Airline;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Flight;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight.IAirlineService;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight.IFlightService;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight.ILocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FlightRestController {
    @Autowired
    private IFlightService flightService;

    @Autowired
    private ILocationService locationService;
    @Autowired
    private IAirlineService airlineService;


    @GetMapping("/flight")
    public ResponseEntity<Page<Flight>> getAllFlight(@RequestParam Optional<String> flightCode,
                                                     @RequestParam Optional<String> flightPrice,
                                                     @RequestParam Optional<String> airlineName,
                                                     @RequestParam Optional<String> departureTime,
                                                     @RequestParam Optional<String> flightDate,
                                                     @RequestParam Integer page) {
        String str1 = "";
        String str2= "";
        String str3= "";
        String str4= "";
        String str5= "";
        if(flightCode.isPresent()){
            str1 = flightCode.get();
            Page<Flight> flights = this.flightService.findBycode(str1, PageRequest.of(page,5));
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }else if(flightPrice.isPresent()) {
            str2 = flightPrice.get();
            Page<Flight> flights = this.flightService.findByPrice(str2,PageRequest.of(page,5));
            return new ResponseEntity<>(flights,HttpStatus.OK);
        }else if(airlineName.isPresent()){
            str3 = airlineName.get();
            Page<Flight> flights = this.flightService.findByAirline(str3,PageRequest.of(page,5));
            return new ResponseEntity<>(flights,HttpStatus.OK);
        }else if(departureTime.isPresent()) {
            str4 = departureTime.get();
            Page<Flight> flights = this.flightService.findByDepartureTime(str4, PageRequest.of(page, 5));
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }else if(flightDate.isPresent()) {

            str5 = flightDate.get();
            Page<Flight> flights = this.flightService.findByDate(str5, PageRequest.of(page, 5));
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            Page<Flight> listFlight = this.flightService.findAllFlightPage(PageRequest.of(page,5));
            return new ResponseEntity<>(listFlight,HttpStatus.OK);
        }
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<Flight> deleteById(@PathVariable Long id){
        Flight flight = this.flightService.findById(id);
        flightService.deleteId(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }



    @GetMapping(value = "/flight/get-all-location")
    public ResponseEntity<List<Location>> getAllLocation(){
        List<Location> locationList = (List<Location>) this.locationService.findAllLocation();
        if(locationList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(locationList,HttpStatus.OK);
        }
    }
    @GetMapping(value = "/flight/get-all-airline")
    public ResponseEntity<List<Airline>> getAllAirline(){
        List<Airline> airlineList = (List<Airline>) this.airlineService.findAllAirline();
        if(airlineList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(airlineList,HttpStatus.OK);
        }
    }


    @GetMapping(value ="/flight-find/{id}")
    public ResponseEntity<Flight>findFlightId(@PathVariable Long id){
        Flight flight = this.flightService.findFlightById(id);
        if (flight == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(flight,HttpStatus.OK);
        }
    }


    @PostMapping(value ="/flight/create")
    public ResponseEntity<List<ObjectError>> createFlight(@Valid @RequestBody FlightDto flightDTO, BindingResult bindingResult){

        new FlightDto().validate(flightDTO,bindingResult);
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }else {
            Flight flight =new Flight();
            BeanUtils.copyProperties(flightDTO,flight);
            this.flightService.saveFlight(flight);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @PutMapping(value ="/flight/update/{id}")
    public ResponseEntity<List<ObjectError>> updateFlight(@PathVariable Long id ,@Valid @RequestBody FlightDto flightDTO,BindingResult bindingResult){
        Flight flight = this.flightService.findFlightById(id);
        flightDTO.setFlightId(flight.getFlightId());
        new FlightDto().validate(flightDTO,bindingResult);
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }else {
            Flight flight1 = new Flight();
            BeanUtils.copyProperties(flightDTO,flight1);
            this.flightService.saveFlight(flight1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
