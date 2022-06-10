package com.backend.airline_tickets_agency_management.model.service.news;
import com.backend.airline_tickets_agency_management.model.entity.news.Category;
import com.backend.airline_tickets_agency_management.model.entity.news.News;
import com.backend.airline_tickets_agency_management.model.repository.news.ICategoryRepository;
import com.backend.airline_tickets_agency_management.model.repository.news.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService{
@Autowired
private INewsRepository newsRepository;
@Autowired
private ICategoryRepository categoryRepository;

    @Override
    public Page<News> findAllWithKeyWord(Pageable pageable, String keyword) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> findAllNormal() {
        return newsRepository.findAll();
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public void remove(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<News> getAllNews(Pageable pageable) {
        return this.newsRepository.getAllNews(pageable);
    }

    @Override
    public List<News> hotNews() {
        return this.newsRepository.hotNews();
    }
}
