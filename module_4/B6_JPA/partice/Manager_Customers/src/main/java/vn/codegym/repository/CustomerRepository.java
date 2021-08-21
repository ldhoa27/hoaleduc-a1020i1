package vn.codegym.repository;

import vn.codegym.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void remove(Customer customer);
    List<Customer> searchByName(String kq);
    void update(Customer customer);
}
