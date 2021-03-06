package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findByName(String name, Pageable pageable);
}