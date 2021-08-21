package vn.codegym.service;

import vn.codegym.model.entity.AppUser;
import vn.codegym.model.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    List<UserRole> findAll();
    void save(UserRole userRole);
    Set<UserRole> findByAppUser(AppUser appUser);
    void deleteAllByUsername(String username);
}
