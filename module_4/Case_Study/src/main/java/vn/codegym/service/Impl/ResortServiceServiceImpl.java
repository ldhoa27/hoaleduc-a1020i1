package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.model.service.ResortService;
import vn.codegym.model.service.ServiceType;
import vn.codegym.repository.ResortServiceRepository;
import vn.codegym.service.ResortServiceService;

import java.util.List;

@Service
public class ResortServiceServiceImpl implements ResortServiceService {
    @Autowired
    private ResortServiceRepository resortServiceRepository;

    @Override
    public Page<ResortService> findAll(Pageable pageable) {
        return resortServiceRepository.findAll(pageable);
    }

    @Override
    public List<ResortService> findAll() {
        return resortServiceRepository.findAll();
    }

    @Override
    public Page<ResortService> findAllByServiceType(ServiceType serviceType, Pageable pageable) {
        return resortServiceRepository.findAllByServiceType(serviceType, pageable);
    }

    @Override
    public Page<ResortService> findAllByIdOrNameContains(String search, Pageable pageable) {
        return resortServiceRepository.findAllByIdContainsOrNameContains(search, search, pageable);
    }

    @Override
    public ResortService save(ResortService resortService) {
        return resortServiceRepository.save(resortService);
    }

    @Override
    public ResortService findById(String id) {
        return resortServiceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existById(String id) {
        return resortServiceRepository.existsById(id);
    }

    @Override
    public Page<ResortService> findByName(String name, Pageable pageable) {
        return resortServiceRepository.findAllByNameContains(name, pageable);
    }
}
