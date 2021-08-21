package vn.codegym.service;

import vn.codegym.model.SmartPhone;

import java.util.List;

public interface SmartphoneService {
    Iterable<SmartPhone> findAll();
    SmartPhone findById(Integer id);
    SmartPhone save(SmartPhone phone);
    SmartPhone remove(Integer id);
}
