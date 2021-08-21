package vn.vku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vku.model.Category;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
