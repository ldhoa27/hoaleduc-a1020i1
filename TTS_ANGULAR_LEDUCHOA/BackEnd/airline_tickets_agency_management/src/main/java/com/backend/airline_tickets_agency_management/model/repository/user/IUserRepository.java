package com.backend.airline_tickets_agency_management.model.repository.user;

import com.backend.airline_tickets_agency_management.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    Boolean existsByUserName(String username);
}