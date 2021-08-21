package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.codegym.model.Customer;
import vn.codegym.model.Province;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Page<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByFirstNameContaining(String name, Pageable pageable);
}