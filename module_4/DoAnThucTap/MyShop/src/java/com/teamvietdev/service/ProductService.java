package com.teamvietdev.service;

import com.teamvietdev.model.Product;
import java.util.List;

/**
 *
 * @author TVD
 */
public interface ProductService {

    // create
    public boolean create(Product object);

    // update
    public boolean update(Product object);

    // delete
    public boolean delete(Product object);

    // find by id
    public Product findById(long productId);

    // load list product by category
    public List<Product> getListByCategory(long categoryId);

    // load list product by featured
    public List<Product> getListFeatured(int limit);

    // load list product by sale
    public List<Product> getListSale(int limit);

    // load list product by nav
    public List<Product> getListNav(int start, int limit);

    // total item
    public int totalItem();
}
