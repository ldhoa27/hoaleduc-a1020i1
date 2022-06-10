package com.backend.airline_tickets_agency_management.model.repository.customer;

import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Query(value = "select * from customer where flag = true " ,nativeQuery = true)
    Page<Customer> findAll(Pageable pageable);

    @Query(value = "{call searchCustomer(?1,?2)}", nativeQuery = true)
    List<Customer> search(String field, String search);

    @Query(value = "select * from customer where customer_name like ?1 and  flag = true " ,nativeQuery = true)
    Page<Customer> searchCustomerByName( String search, Pageable pageable);
    @Query(value = "select * from customer where customer_email like:keyword and  flag = true " ,nativeQuery = true)
    Page<Customer> searchCustomerByEmail(@Param("keyword") String search, Pageable pageable);

    @Query(value = "select * from customer where customer_code like ?1 and  flag = true " ,nativeQuery = true)
    Page<Customer> searchCustomerByCode( String search, Pageable pageable);

    @Query(value = "select * from customer where customer_birthday like ?1 and  flag = true " ,nativeQuery = true)
    Page<Customer> searchCustomerByBirthday( String search, Pageable pageable);

    @Query(value = "select * from customer ",nativeQuery = true)
    List<Customer> findAllNormal();

    @Query(value = "select * from customer where customer_id = ?1",nativeQuery = true)
    Customer findCustomerById(Long id);

    @Query(value = "select * from customer where customer_email = ?1",nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "select * from customer where customer_phone = ?1",nativeQuery = true)
    Customer findByPhone(String phone);

    @Query(value = "select * from customer where customer_passport = ?1",nativeQuery = true)
    Customer findByPassport(String passport);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO `customer` (`customer_address`, `customer_birthday`, `customer_code`, `customer_email`, `customer_gender`, `customer_name`, `customer_nationality`, `customer_phone`, `flag`, `customer_passport`) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",
    nativeQuery = true)
    void insertCustomer(String address, String birthday, String code, String email, String gender, String name, String nationality, String phone, Boolean flag, String passport);

}
