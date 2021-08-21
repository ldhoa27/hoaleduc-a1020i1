package vn.codegym.service;

import vn.codegym.model.ProductType;

import java.util.List;

public interface LoaiSanPhamService {
    List<ProductType> findAll();
}
