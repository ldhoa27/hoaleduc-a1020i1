package vn.codegym.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.model.Product;

import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository {
    List<Product> getAll();
    void save(Product product);
    void delete(int id);
    Product findById(int id);
    boolean existById(Set<Product> list, int id);


    Product getFirstProduct(Set<Product> listRecommend);
}
