package com.backend.airline_tickets_agency_management.model.repository.user;

import com.backend.airline_tickets_agency_management.model.entity.user.ERole;
import com.backend.airline_tickets_agency_management.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}

