package vn.codegym.service;

import vn.codegym.model.employee.EducationDegree;

import java.util.List;

public interface EducationDegreeService {
    List<EducationDegree> findAll();

    EducationDegree findById(int id);
}
