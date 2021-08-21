package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.model.employee.Employee;
import java.util.List;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void delete (int id);
    Page<Employee> findByName(String search, Pageable pageable);
}
