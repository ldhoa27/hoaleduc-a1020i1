package vn.codegym.repository;

import vn.codegym.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> finAll();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);

}
