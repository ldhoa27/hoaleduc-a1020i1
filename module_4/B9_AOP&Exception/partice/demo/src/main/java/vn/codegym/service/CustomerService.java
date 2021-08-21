package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.exception.DuplicateEmailException;
import vn.codegym.model.Customer;

public interface CustomerService {
    //    List<Customer> findAll() ;
    Page<Customer> findByName(String name, Pageable pageable) throws Exception;
    //
    Customer findById(Long id);

    void save(Customer customer) throws DuplicateEmailException;

    void remove(Long id);


    Page<Customer> findAll(Pageable pageable);

    void update(Customer customer);

}
