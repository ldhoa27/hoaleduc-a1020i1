package vn.codegym.service;

import vn.codegym.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog findById(int id);
    void save(Blog blog);
    void remove(Blog blog);
    List<Blog> searchByName(String kq);
    void update(Blog blog);
}
