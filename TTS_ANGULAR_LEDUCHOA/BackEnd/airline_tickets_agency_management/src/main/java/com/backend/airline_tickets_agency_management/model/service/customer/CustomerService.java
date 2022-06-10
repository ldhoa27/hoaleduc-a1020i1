package com.backend.airline_tickets_agency_management.model.service.customer;

import com.backend.airline_tickets_agency_management.model.dto.customer.CustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.repository.customer.ICustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.Column;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository iCustomerRepository;

    @Override
    public Page<Customer> showListCustomer(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> searchPageCustomer(Pageable pageable, String fieldSearch, String search) {
        if ("name".equals(fieldSearch)) {
            return iCustomerRepository.searchCustomerByName("%" + search + "%", pageable);
        } else if ("email".equals(fieldSearch)) {
            return iCustomerRepository.searchCustomerByEmail("%" + search + "%", pageable);
        } else {
            return iCustomerRepository.searchCustomerByBirthday("%" + search + "%", pageable);
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return this.iCustomerRepository.save(customer);
    }

    @Override
    public Map<String, Object> createCustomer(CustomerDto customerDto, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(ob -> {
                errors.add(ob.getDefaultMessage());
            });
            result.put("status", false);
            result.put("msg", "Thêm mới thất bại");
            result.put("errors", errors);
            return result;
        }
        if (!checkDate(customerDto)) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgDate", "Tuổi phải lớn hơn 18");
            return result;
        }

        if (checkEmail(customerDto)) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgEmail", "Email đã tồn tại");
            return result;
        }
        if (checkPassport(customerDto)) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgPassport", "CMND đã tồn tại");
            return result;
        }

        List<Customer> customerList = this.iCustomerRepository.findAllNormal();
        String code = "";
        if (customerList.isEmpty()) {
            code = "KH001";
        } else {
            Long lastId = customerList.get(customerList.size() - 1).getCustomerId();
            if (lastId < 10) {
                code = "KH00" + (lastId + 1);
            } else if (lastId < 100) {
                code = "KH0" + (lastId + 1);
            }
        }
        customerDto.setCustomerCode(code);
        Customer customer = new Customer();
//        BeanUtils.copyProperties(customerDto, customer);
//        customer.setCustomerCode(code);
        customerDto.setCustomerName(customerDto.getCustomerName().trim());
        customerDto.setCustomerEmail(customerDto.getCustomerEmail().trim());
        customerDto.setCustomerAddress(customerDto.getCustomerAddress().trim());
        BeanUtils.copyProperties(customerDto, customer);


        iCustomerRepository.save(customer);
        result.put("status", true);
        result.put("msg", "Đăng ký khách hàng thành công!!!");
        return result;
    }


    @Override
    public Map<String, Object> updateCustomer(Long id, CustomerDto customerDto,
                                              BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        List<String> errors = new ArrayList<>();
        Customer customer = this.iCustomerRepository.findCustomerById(id);
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(ob -> {
                errors.add(ob.getDefaultMessage());
            });
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("errors", errors);
            return result;
        }
        if (!checkDate(customerDto)) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgDate", "Tuổi phải lớn hơn 18");
            return result;
        }

        if (checkEmail(customerDto) && !customerDto.getCustomerEmail().equals(customer.getCustomerEmail())) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgEmail", "Email đã tồn tại");
            return result;
        }
        if (checkPassport(customerDto) && !customerDto.getCustomerPassport().equals(customer.getCustomerPassport())) {
            result.put("status", false);
            result.put("msg", "UPDATE FAILED");
            result.put("msgPassport", "CMND đã tồn tại");
            return result;
        }
        customerDto.setCustomerName(customerDto.getCustomerName().trim());
        customerDto.setCustomerEmail(customerDto.getCustomerEmail().trim());
        customerDto.setCustomerAddress(customerDto.getCustomerAddress().trim());

        BeanUtils.copyProperties(customerDto, customer);
        customer.setCustomerId(customerDto.getCustomerId());
        iCustomerRepository.save(customer);
        result.put("status", true);
        result.put("msg", "Sửa khách hàng thành công!!!");
        return result;


    }

    public boolean checkEmail(CustomerDto customerDto) {
        Customer customer = this.iCustomerRepository.findByEmail(customerDto.getCustomerEmail());
        if (customer != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassport(CustomerDto customerDto) {
        Customer customer = this.iCustomerRepository.findByPassport(customerDto.getCustomerPassport());
        if (customer != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDate(CustomerDto customerDto) {

        String date = customerDto.getCustomerBirthday();
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        try {
            birthday.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));

        } catch (Exception e) {
            return false;
        }
        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE) - birthday.get(Calendar.DATE);
        if (month < 0 || (day < 0)) {
            age--;
        }
        if (age < 18) {
            return false;
        }
        return true;

    }

    @Override
    public Customer findCustomerById(Long id) {
        return this.iCustomerRepository.findCustomerById(id);
    }

    @Override
    public void insertCustomer(String address, String birthday, String code, String email, String gender, String name, String nationality, String phone, Boolean flag, String passport) {
        this.iCustomerRepository.insertCustomer(address, birthday, code, email, gender, name, nationality, phone, flag, passport);
    }

    @Override
    public List<Customer> findAllNormal() {
        return this.iCustomerRepository.findAllNormal();

    }
}
