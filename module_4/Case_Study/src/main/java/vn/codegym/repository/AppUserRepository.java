package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
}
