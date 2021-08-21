package vn.codegym.service;

import org.springframework.stereotype.Service;
import vn.codegym.model.Category;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
}
