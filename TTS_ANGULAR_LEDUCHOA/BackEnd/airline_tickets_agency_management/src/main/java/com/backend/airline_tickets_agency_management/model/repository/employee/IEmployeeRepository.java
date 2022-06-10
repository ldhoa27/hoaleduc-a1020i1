package com.backend.airline_tickets_agency_management.model.repository.employee;
import com.backend.airline_tickets_agency_management.model.dto.employee.IEmployeeDto;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "{call searchEmployee (?1, ?2)}", nativeQuery = true)
    List<IEmployeeDto> getAllEmployee(String typeSearch, String valueSearch);

}
