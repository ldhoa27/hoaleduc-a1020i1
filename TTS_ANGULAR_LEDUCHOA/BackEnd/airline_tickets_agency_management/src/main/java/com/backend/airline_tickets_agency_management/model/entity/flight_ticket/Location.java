package com.backend.airline_tickets_agency_management.model.entity.flight_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;
    private String cityName;
    private String airportName;
    private String areaCode;

    @OneToMany(mappedBy = "locationTo")
    @JsonIgnore
    List<Flight> flightsTo;

    @OneToMany(mappedBy = "locationFrom")
    @JsonIgnore
    List<Flight> flightsFrom;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<Flight> getFlightsTo() {
        return flightsTo;
    }

    public void setFlightsTo(List<Flight> flightsTo) {
        this.flightsTo = flightsTo;
    }

    public List<Flight> getFlightsFrom() {
        return flightsFrom;
    }

    public void setFlightsFrom(List<Flight> flightsFrom) {
        this.flightsFrom = flightsFrom;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) &&
                Objects.equals(cityName, location.cityName) &&
                Objects.equals(airportName, location.airportName) &&
                Objects.equals(areaCode, location.areaCode);

    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, cityName, airportName, areaCode);
    }

}

