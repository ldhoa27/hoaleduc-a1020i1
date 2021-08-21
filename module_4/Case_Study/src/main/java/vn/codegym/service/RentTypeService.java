package vn.codegym.service;

import vn.codegym.model.service.RentType;

import java.util.List;

public interface RentTypeService {
    List<RentType> findAll();
}