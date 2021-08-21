package vn.codegym.service;

import org.springframework.stereotype.Service;
import vn.codegym.model.AppRole;

@Service
public interface AppRoleService {
    AppRole findById(int id);
}