package vn.codegym.service;

import vn.codegym.model.employee.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(int id);
}
