package vn.codegym.service;


import vn.codegym.entity.Book;
import vn.codegym.entity.Code;
import vn.codegym.entity.Status;
import vn.codegym.repository.IBookRepository;
import vn.codegym.repository.ICodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService implements ICodeService {

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    ICodeRepository codeRepository;

    @Override
    public List<Code> findAll() {
        return this.codeRepository.findAll();
    }

    @Override
    public Code findById(Integer id) {
        return codeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Code code) {
        codeRepository.save(code);
    }

    @Override
    public List<Code> findAllCodeByBookId(Integer id) {
        return codeRepository.findCodeByBook_Id(id);
    }

    @Override
    public List<Code> findAvailableCodeByBookId(Integer id) {
        return codeRepository.findCodeByBook_IdAndStatus_Id(id, 1);
    }

    @Override
    public List<Code> findUsedCodeByBookId(Integer id) {
        return codeRepository.findCodeByBook_IdAndStatus_Id(id, 2);
    }

    @Override
    public void returnBookByCode(Book book, Integer returnCodeId) {
        Code code = codeRepository.findById(returnCodeId).orElse(null);
        book = code.getBook();
        book.returnBook();
        bookRepository.save(book);
        code.setStatus(new Status(1, "available"));
    }


}
