package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.model.Product;
import vn.codegym.repository.ProductRepository;
import vn.codegym.service.SanPhamService;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private ProductRepository sanPhamRepository;

    @Override
    public Page<Product> findAllSanPham(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public Product findSanPhamById(int id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSanPham(Product sanPham) {
        sanPhamRepository.save(sanPham);
    }

    @Override
    public void deleteSanPhamById(int id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public Page<Product> findSanPhamByNameContaining(String name, Pageable pageable) {
        return sanPhamRepository.findProductByNameProductContaining(name, pageable);
    }
}