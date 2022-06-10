package com.backend.airline_tickets_agency_management.model.service.user;

import com.backend.airline_tickets_agency_management.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Optional;

public interface IUserService {
    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    void save(User user);

   }
