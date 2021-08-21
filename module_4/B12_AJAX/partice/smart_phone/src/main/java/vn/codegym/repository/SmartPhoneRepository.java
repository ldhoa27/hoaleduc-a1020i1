package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.SmartPhone;
@Repository
public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Integer> {
}
