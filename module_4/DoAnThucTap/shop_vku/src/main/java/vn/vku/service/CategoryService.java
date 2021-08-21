package vn.vku.service;

import vn.vku.model.Category;

import java.util.List;

public interface CategoryService {

    // create
     void create(Category category);

    // update
     void update(Category category);

    // delete
    void delete(Category category);

    // find by id
     Category findById(long categoryId);

    // load list category
     List<Category> getAll();
}
