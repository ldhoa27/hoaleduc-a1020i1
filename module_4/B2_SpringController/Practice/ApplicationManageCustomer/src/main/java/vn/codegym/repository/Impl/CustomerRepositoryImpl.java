package vn.codegym.repository.Impl;

import vn.codegym.model.Customer;
import vn.codegym.repository.CustomerRepository;
import vn.codegym.service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        customers.add( new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        customers.add( new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        customers.add( new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        customers.add( new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        customers.add(new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        customers.add( new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> finAll() {
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }



}
