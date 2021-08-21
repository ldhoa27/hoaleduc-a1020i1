package vn.codegym.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.model.Province;
@Repository
public interface ProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
