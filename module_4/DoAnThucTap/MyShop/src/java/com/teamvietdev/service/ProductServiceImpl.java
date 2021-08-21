package com.teamvietdev.service;

import com.teamvietdev.dao.ProductDAO;
import com.teamvietdev.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TVD
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean create(Product object) {
        return productDAO.create(object);
    }

    @Override
    public boolean update(Product object) {
        return productDAO.update(object);
    }

    @Override
    public boolean delete(Product object) {
        return productDAO.delete(object);
    }

    @Override
    public Product findById(long categoryId) {
        return productDAO.findById(categoryId);
    }

    @Override
    public List<Product> getListByCategory(long categoryId) {
        return productDAO.getListByCategory(categoryId);
    }

    @Override
    public List<Product> getListFeatured(int limit) {
        return productDAO.getListFeatured(limit);
    }

    @Override
    public List<Product> getListSale(int limit) {
        return productDAO.getListSale(limit);
    }

    @Override
    public List<Product> getListNav(int start, int limit) {
        return productDAO.getListNav(start, limit);
    }

    @Override
    public int totalItem() {
        return productDAO.totalItem();
    }

}
