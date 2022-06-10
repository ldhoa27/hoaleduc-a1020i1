
package com.backend.airline_tickets_agency_management.controller;

import com.backend.airline_tickets_agency_management.model.dto.news.NewsDto;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import com.backend.airline_tickets_agency_management.model.entity.news.Category;

import com.backend.airline_tickets_agency_management.model.entity.news.News;
import com.backend.airline_tickets_agency_management.model.service.employee.IEmployeeService;
import com.backend.airline_tickets_agency_management.model.service.news.INewsService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/news")
public class NewsRestController {


    private final IEmployeeService employeeService;
    private final INewsService newsService;

    public NewsRestController(IEmployeeService employeeService, INewsService newsService) {
        this.employeeService = employeeService;
        this.newsService = newsService;
    }

    @PostMapping(value = "")
    public ResponseEntity<News> create(@Valid @RequestBody NewsDto newsDto) throws ObjectNotFoundException {
        checkFounding(newsDto);
        News news = new News();
        BeanUtils.copyProperties(newsDto, news);
        this.newsService.save(news);
        return new ResponseEntity<>(news, HttpStatus.OK);

    }

    private void checkFounding(NewsDto newsDto) throws ObjectNotFoundException {
        Optional<Employee> employee = employeeService.findById(newsDto.getEmployee().getEmployeeId());
        if (!employee.isPresent()) {
            throw new ObjectNotFoundException("employee");
        }
        Optional<Category> category = newsService.getCategoryById(newsDto.getCategory().getCategoryId());
        if (!category.isPresent()) {
            throw new ObjectNotFoundException("category");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<News> update(@Valid @RequestBody NewsDto newsDto, @PathVariable Long id) throws ObjectNotFoundException {
        Optional<News> newsEntity = this.newsService.findById(id);
        if (!newsEntity.isPresent()) {
            throw new ObjectNotFoundException("news");
        }
        checkFounding(newsDto);
        News news = newsEntity.get();
        BeanUtils.copyProperties(newsDto, news);
        this.newsService.save(news);
        return new ResponseEntity<>(news, HttpStatus.OK);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<News> getNews(@PathVariable Long id) {
        Optional<News> news = newsService.findById(id);
        return news.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = newsService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> category = newsService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/update-views/{id}")
    public ResponseEntity<News> updateViews(@PathVariable Long id) {
        News newsOptional = this.newsService.findById(id).orElse(null);
        if (newsOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newsOptional.setNewsViews(newsOptional.getNewsViews() + 1);
        newsService.save(newsOptional);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/news-list")
    public ResponseEntity<Page<News>> listNews(@RequestParam Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        Page<News> newsPage = this.newsService.getAllNews(pageable);
        if (newsPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @GetMapping(value = "/hot-news")
    public ResponseEntity<List<News>> hotNews() {
        List<News> newsPage = this.newsService.hotNews();
        if (newsPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @DeleteMapping("/news-delete/{id}")
    public ResponseEntity<News> deleteNews(@PathVariable Long id) {
        News news = this.newsService.findById(id).orElse(null);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!news.isFlag()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        news.setFlag(false);
        this.newsService.save(news);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ObjectNotFoundException.class)
    public Map<String, String> handleNotFound(ObjectNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

}
