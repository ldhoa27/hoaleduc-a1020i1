package com.backend.airline_tickets_agency_management.model.service.user;

import com.backend.airline_tickets_agency_management.model.entity.user.Role;

public interface IRoleService {
    Iterable<Role> findAll();
}
