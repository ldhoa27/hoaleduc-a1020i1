package vn.codegym.service;

import vn.codegym.model.entity.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> findAll();
    AppUser findByUsername(String username);
    void save(AppUser appUser);
    void delete(String username);
    boolean existById(String username);
}
