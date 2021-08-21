package vn.vku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vku.model.Product;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getListByCategory(long categoryId);
    List<Product> getListByCategoryAndLimit(long categoryId);
    List<Product> getListFeatured(int limit);
    List<Product> getListSale(int limit);
    List<Product> getListNav(int start, int limit);
}
