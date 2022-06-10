package com.backend.airline_tickets_agency_management.model.service.destination;

import com.backend.airline_tickets_agency_management.model.dto.destination.ScenicDto;
import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Destination;
import com.backend.airline_tickets_agency_management.model.entity.destinations_scenic.Scenic;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IScenicService {
    Map<String,Object> save(ScenicDto scenicDto, BindingResult bindingResult);
    Map<String,Object> update(ScenicDto scenicDto, BindingResult bindingResult);
    Iterable<Scenic> getScenicByDestination(Long idDestination);
    void delScenic(Long id);
    Optional<Scenic> findById(Long id);
}
