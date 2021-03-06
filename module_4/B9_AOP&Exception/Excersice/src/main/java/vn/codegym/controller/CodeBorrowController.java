package vn.codegym.controller;

import vn.codegym.entity.Book;
import vn.codegym.entity.Code;
import vn.codegym.exception.NotAvailableException;
import vn.codegym.exception.NotBorrowException;
import vn.codegym.exception.WrongCodeException;
import vn.codegym.service.IBookService;
import vn.codegym.service.ICodeService;
import vn.codegym.service.IStatusService;
import vn.codegym.validation.ReturnCodeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CodeBorrowController {
    @Autowired
    IBookService bookService;

    @Autowired
    ICodeService codeService;

    @Autowired
    IStatusService statusService;

    @GetMapping("/viewborrow")
    public String view(Model model) {
        List<Code> codeBorrowList = codeService.findAll();
        model.addAttribute("codeBorrowList", codeBorrowList);
        return "code/view_code_borrow";
    }

    @GetMapping("/returnCode")
    public String returnPage(Model model, @RequestParam Integer id) {

        Code code = codeService.findById(id);
        Book book = code.getBook();

        model.addAttribute("book", book);
        model.addAttribute("code", code);
        return "code/returnbook";
    }

    @PostMapping("/returnCode")
    public String returnBook(Model model, @ModelAttribute Book book, @ModelAttribute Code code) throws WrongCodeException, NotAvailableException {

        bookService.returnBook(book, code.getCode());
        return "redirect:books";
    }


}