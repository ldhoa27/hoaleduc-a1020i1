package vn.vku.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vku.model.Product;
import vn.vku.repository.ProductRepository;
import vn.vku.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getListByCategory(long categoryId) {
        return productRepository.getListByCategory(categoryId);
    }

    @Override
    public List<Product> getListFeatured(int limit) {
        return productRepository.getListFeatured(limit);
    }

    @Override
    public List<Product> getListSale(int limit) {
        return productRepository.getListSale(limit);
    }

    @Override
    public List<Product> getListNav(int start, int limit) {
        return productRepository.getListNav(start, limit);
    }
}
