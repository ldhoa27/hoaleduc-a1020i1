package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.entity.AppUser;
import vn.codegym.model.entity.UserRole;
import vn.codegym.repository.UserRoleRepository;
import vn.codegym.service.UserRoleService;

import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public Set<UserRole> findByAppUser(AppUser appUser) {
        return userRoleRepository.findByAppUser(appUser);
    }

    @Override
    public void deleteAllByUsername(String username) {
        userRoleRepository.deleteUserRolesByAppUser_Username(username);
    }
}
