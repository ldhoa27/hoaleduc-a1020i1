package vn.codegym.service;


import vn.codegym.entity.Book;
import vn.codegym.entity.Code;
import vn.codegym.exception.NotAvailableException;
import vn.codegym.exception.WrongCodeException;

import java.util.List;

public interface ICodeService {
    List<Code> findAll();

    Code findById(Integer id);

    void save(Code code);

    List<Code> findAllCodeByBookId(Integer id);

    List<Code> findAvailableCodeByBookId(Integer id);

    List<Code> findUsedCodeByBookId(Integer id);

    void returnBookByCode(Book book, Integer returnCode);


}
