package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.model.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
}
