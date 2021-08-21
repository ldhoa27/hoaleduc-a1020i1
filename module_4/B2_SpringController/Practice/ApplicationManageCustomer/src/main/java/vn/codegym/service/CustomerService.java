package vn.codegym.service;

import vn.codegym.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> finAll();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
}
