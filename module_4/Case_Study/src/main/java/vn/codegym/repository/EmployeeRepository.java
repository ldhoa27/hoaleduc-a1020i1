package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.model.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findEmployeesByNameContains(String name, Pageable pageable);
//    @Query("SELECT e FROM Employee e WHERE e.appUser.username = :username")
//    Employee findAllByAppUser_Username(String username);
}