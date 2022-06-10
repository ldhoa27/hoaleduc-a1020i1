package com.backend.airline_tickets_agency_management.model.service.employee;

import com.backend.airline_tickets_agency_management.model.dto.employee.IEmployeeDto;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import com.backend.airline_tickets_agency_management.model.repository.employee.IEmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> findAllWithKeyWord(Pageable pageable, String keyword) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAllNormal() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public Page<IEmployeeDto> getAllEmployee(String typeSearch, String valueSearch, Integer page) {
        List<IEmployeeDto> employeeList = employeeRepository.getAllEmployee(typeSearch, valueSearch);
        Pageable pageable = PageRequest.of(page, 5);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), employeeList.size());
        return new PageImpl<>(employeeList.subList(start, end), pageable, employeeList.size());
    }
}
