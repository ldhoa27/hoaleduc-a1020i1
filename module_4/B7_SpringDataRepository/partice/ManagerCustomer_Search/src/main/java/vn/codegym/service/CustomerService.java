package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.codegym.model.Customer;
import vn.codegym.model.Province;

import java.awt.print.Pageable;
@Service
public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
