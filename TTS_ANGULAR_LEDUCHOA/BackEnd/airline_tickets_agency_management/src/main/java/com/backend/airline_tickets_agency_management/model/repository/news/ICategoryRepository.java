package com.backend.airline_tickets_agency_management.model.repository.news;
import com.backend.airline_tickets_agency_management.model.entity.news.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
