package vn.codegym.service;

import org.springframework.stereotype.Service;
import vn.codegym.model.AppUser;
import vn.codegym.model.UserRole;

import java.util.Set;

@Service
public interface UserRoleService {
    Set<UserRole> findByAppUser(AppUser appUser);
}
