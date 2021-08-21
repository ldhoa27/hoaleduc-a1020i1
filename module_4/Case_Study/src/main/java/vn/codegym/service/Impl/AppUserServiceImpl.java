package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.entity.AppUser;
import vn.codegym.repository.AppUserRepository;
import vn.codegym.service.AppUserService;

import java.util.List;


@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findById(username).orElse(null);
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void delete(String username) {
        appUserRepository.deleteById(username);
    }

    @Override
    public boolean existById(String username) {
        return appUserRepository.existsById(username);
    }
}