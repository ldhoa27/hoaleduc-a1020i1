package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.AppRole;
import vn.codegym.repository.AppRoleRepository;
import vn.codegym.service.AppRoleService;

@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public AppRole findById(int id) {
        return appRoleRepository.findById(id).orElse(null);
    }

}