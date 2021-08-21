package vn.codegym.repository;


import vn.codegym.entity.Book;
import vn.codegym.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICodeRepository extends JpaRepository<Code, Integer> {
    List<Code> findCodeByBook_Id(Integer id);

    List<Code> findCodeByBook_IdAndStatus_Id(Integer bookId, Integer statusId);


}
