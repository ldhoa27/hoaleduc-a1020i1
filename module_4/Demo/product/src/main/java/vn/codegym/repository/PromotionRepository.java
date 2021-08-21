package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.Promotion;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findAllByDiscount(long discount);
    List<Promotion> findAllByStartDate(String date);
    List<Promotion> findAllByEndDate(String date);
    List<Promotion> findAllByStartDateOrEndDate(String startDate, String endDate);

}
