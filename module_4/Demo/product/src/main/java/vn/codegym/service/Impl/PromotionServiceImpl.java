package vn.codegym.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.model.Promotion;
import vn.codegym.repository.PromotionRepository;
import vn.codegym.service.PromotionService;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public Promotion findById(int id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> findByDiscount(long discount) {
        return promotionRepository.findAllByDiscount(discount);
    }

    @Override
    public List<Promotion> findByStartDate(String startDate) {
        return promotionRepository.findAllByStartDate(startDate);
    }

    @Override
    public List<Promotion> findByEndDate(String endDate) {
        return promotionRepository.findAllByEndDate(endDate);
    }

    @Override
    public List<Promotion> findByDate(String search) {
        return promotionRepository.findAllByStartDateOrEndDate(search, search);
    }
}