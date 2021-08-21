package vn.codegym.service;

import vn.codegym.model.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> findAll();

    void save(Promotion promotion);
    Promotion findById(int id);

    void delete(int id);

    List<Promotion> findByDiscount(long discount);

    List<Promotion> findByStartDate(String startDate);

    List<Promotion> findByEndDate(String endDate);

    List<Promotion> findByDate(String search);
}
