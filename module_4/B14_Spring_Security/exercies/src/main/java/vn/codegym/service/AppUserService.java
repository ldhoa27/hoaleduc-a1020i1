package vn.codegym.service;

import org.springframework.stereotype.Service;
import vn.codegym.model.AppUser;

@Service
public interface AppUserService {
    AppUser findByUsername(String username);
}