package com.backend.airline_tickets_agency_management.model.service.customer;
import com.backend.airline_tickets_agency_management.model.dto.customer.CustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    Page<Customer> showListCustomer(Pageable pageable);

    Page<Customer> searchPageCustomer(Pageable pageable, String field, String search);

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long id);

    Map<String, Object> updateCustomer(Long id,CustomerDto customerDto,BindingResult bindingResult);

    Map<String, Object> createCustomer(CustomerDto customerDto,BindingResult bindingResult);


    void insertCustomer(String address, String birthday, String code, String email, String gender, String name, String nationality, String phone, Boolean flag, String passport);

    List<Customer> findAllNormal();

}
