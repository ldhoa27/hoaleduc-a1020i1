package com.backend.airline_tickets_agency_management.model.repository.news;
import com.backend.airline_tickets_agency_management.model.entity.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News,Long> {
    @Query(value = "select * from news where flag = 1", nativeQuery = true)
    Page<News> getAllNews(Pageable pageable);

    @Query(value = "select * from news where flag=1  order by news_views desc limit 0,3 ", nativeQuery = true)
    List<News> hotNews();
}
