package vn.codegym.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.entity.AppUser;
import vn.codegym.model.entity.UserRole;
import vn.codegym.model.entity.UserRoleKey;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    Set<UserRole> findByAppUser(AppUser appUser);
    void deleteUserRolesByAppUser_Username(String username);
}