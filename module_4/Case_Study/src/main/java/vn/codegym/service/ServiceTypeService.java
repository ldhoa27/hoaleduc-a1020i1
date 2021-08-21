package vn.codegym.service;

import vn.codegym.model.service.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceType> findAll();

    ServiceType findById(int id);
}
