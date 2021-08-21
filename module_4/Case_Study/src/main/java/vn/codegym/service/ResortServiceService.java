package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.model.service.ResortService;
import vn.codegym.model.service.ServiceType;

import java.util.List;

public interface ResortServiceService {
    Page<ResortService> findAll(Pageable pageable);
    List<ResortService> findAll();
    Page<ResortService> findAllByServiceType(ServiceType serviceType, Pageable pageable);
    Page<ResortService> findAllByIdOrNameContains(String search, Pageable pageable);
    ResortService save(ResortService resortService);
    ResortService findById(String id);
    boolean existById(String id);
    Page<ResortService> findByName(String name, Pageable pageable);
}
