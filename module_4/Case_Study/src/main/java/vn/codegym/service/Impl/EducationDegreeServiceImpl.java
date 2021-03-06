package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.employee.EducationDegree;
import vn.codegym.repository.EducationDegreeRepository;

import java.util.List;

@Service
public class EducationDegreeServiceImpl implements vn.codegym.service.EducationDegreeService {
    @Autowired
    EducationDegreeRepository educationDegreeRepository;
    @Override
    public List<EducationDegree> findAll() {
        return educationDegreeRepository.findAll();
    }

    @Override
    public EducationDegree findById(int id) {
        return educationDegreeRepository.findById(id).orElse(null);
    }
}
