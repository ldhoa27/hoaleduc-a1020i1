package com.backend.airline_tickets_agency_management.controller.flight_ticket.flight;


import com.backend.airline_tickets_agency_management.model.dto.flight_ticket.LocationDto;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Location;
import com.backend.airline_tickets_agency_management.model.service.flight_ticket.flight.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.BeanUtils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("location")

public class LocationRestController {

    @Autowired
    private ILocationService locationService;

    @GetMapping("/list-location")
    public ResponseEntity<List<Location>> showListLocation(){
        List<Location> locationList = (List<Location>) this.locationService.findAllLocation();
        if(locationList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(locationList,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable Long id){
        Optional<Location> location = this.locationService.findLocationById(id);
        if(!location.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(location.get(),HttpStatus.OK);
        }

    }
    @PostMapping("/create-location")
    public ResponseEntity<List<ObjectError>> createLocation(@Valid @RequestBody LocationDto locationDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }else {
            Location location =new Location();
            BeanUtils.copyProperties(locationDto,location);
            this.locationService.saveLocation(location);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id){
        Optional<Location> location = this.locationService.findLocationById(id);
        if(!location.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            this.locationService.removeLocation(id);
            return new ResponseEntity<>(location.get(),HttpStatus.OK);
        }
    }
    @GetMapping()
    public ResponseEntity<Location> findLocationByCityName(@RequestParam String cityName){
        Optional<Location> location = this.locationService.findLocationByCityName(cityName);
        if(!location.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(location.get(),HttpStatus.OK);
        }

    }
}
