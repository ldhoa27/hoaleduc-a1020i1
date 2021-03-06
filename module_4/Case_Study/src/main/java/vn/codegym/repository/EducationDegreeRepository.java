package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.employee.EducationDegree;

@Repository
public interface EducationDegreeRepository extends JpaRepository<EducationDegree, Integer> {
}
