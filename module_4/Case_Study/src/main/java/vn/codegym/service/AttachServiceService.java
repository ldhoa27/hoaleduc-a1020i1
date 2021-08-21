package vn.codegym.service;

import vn.codegym.model.service.AttachService;

import java.util.List;

public interface AttachServiceService {
    List<AttachService> findAll();
    AttachService findById(int id);
}
