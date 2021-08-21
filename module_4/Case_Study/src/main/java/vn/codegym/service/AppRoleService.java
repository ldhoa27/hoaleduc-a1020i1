package vn.codegym.service;

import vn.codegym.model.entity.AppRole;

import java.util.List;

public interface AppRoleService {
    List<AppRole> findAll();
    AppRole findById(int id);
}
