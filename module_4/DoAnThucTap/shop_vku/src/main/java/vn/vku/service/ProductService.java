package vn.vku.service;

import vn.vku.model.Product;

import java.util.List;

public interface ProductService {
    // create
     void create(Product product);

    // update
    void update(Product product);

    // delete
    void delete(Product product);

    // find by id
    Product findById(long productId);

    // load list product by category
     List<Product> getListByCategory(long categoryId);

    // load list product by featured
     List<Product> getListFeatured(int limit);

    // load list product by sale
     List<Product> getListSale(int limit);

    // load list product by nav
     List<Product> getListNav(int start, int limit);
}
