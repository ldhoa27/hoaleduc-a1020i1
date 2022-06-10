package com.backend.airline_tickets_agency_management.model.service.employee;


import com.backend.airline_tickets_agency_management.model.dto.employee.IEmployeeDto;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import com.backend.airline_tickets_agency_management.model.service.IGeneralService;
import org.springframework.data.domain.Page;

public interface IEmployeeService extends IGeneralService<Employee> {
    Page<IEmployeeDto> getAllEmployee(String typeSearch, String valueSearch, Integer page);

}
