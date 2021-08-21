package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.service.ResortService;
import vn.codegym.model.service.ServiceType;

@Repository
public interface ResortServiceRepository extends JpaRepository<ResortService, String> {
    Page<ResortService> findAll(Pageable pageable);
    Page<ResortService> findAllByNameContains(String name, Pageable pageable);
    Page<ResortService> findAllByServiceType(ServiceType serviceType, Pageable pageable);
    Page<ResortService> findAllByIdContainsOrNameContains(String id, String name, Pageable pageable);
}