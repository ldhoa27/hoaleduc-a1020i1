package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.model.ProductType;
import vn.codegym.repository.ProductTypeRepository;
import vn.codegym.service.LoaiSanPhamService;

import java.util.List;

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Autowired
    private ProductTypeRepository loaiSanPhamRepository;

    @Override
    public List<ProductType> findAll() {
        return loaiSanPhamRepository.findAll();
    }
}
