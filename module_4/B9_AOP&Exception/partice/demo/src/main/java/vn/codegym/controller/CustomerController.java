package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.exception.DuplicateEmailException;
import vn.codegym.model.Customer;
import vn.codegym.service.CustomerService;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService ;

    @GetMapping("/customer-list")
    public String showList(Model model, @PageableDefault(value = 5) Pageable pageable){
        Page<Customer> customers = customerService.findAll(pageable);
        model.addAttribute("customer",customers);
        return "/customer/customer-list";
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam Optional<String> key_search, @PageableDefault(value = 5) Pageable pageable, Model model){
        if(!key_search.isPresent()){
            return new ModelAndView("customer/customer-list", "customer", customerService.findAll(pageable));
        }else {
            try {
                model.addAttribute("key_search", key_search.get());
                return new ModelAndView("customer/customer-list", "customer", customerService.findByName(key_search.get(), pageable));
            } catch (Exception e)
            {
                return new ModelAndView("redirect:/customer-list");
            }
        }
    }
    @GetMapping("/customer-create")
    public ModelAndView showFromAddCustomer(){
        Customer customer = new Customer();
        ModelAndView modelAndView = new ModelAndView("/customer/customer-create","customer", customer);
        return modelAndView;
    }
    @PostMapping("/customer-create")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) throws DuplicateEmailException {
        customerService.save(customer);
        return new ModelAndView("redirect:/customer-list");
    }
    // Handle exception
    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("customer/inputs-not-acceptable");
    }

    @GetMapping("/{id}/customer-delete")
    public String deleteCustomer(@PathVariable Long id){
        customerService.remove(id);
        return "redirect:/customer-list";
    }

    @GetMapping("/{id}/customer-edit")
    public ModelAndView showFromEdit(@PathVariable Long id){
        try {
            ModelAndView modelAndView = new ModelAndView("customer/customer-edit", "customer", customerService.findById(id));
            return modelAndView;
        }catch (Exception e){
            return new ModelAndView("redirect:/customer-list");
        }
    }
    @PostMapping("/customer-edit")
    public  String editCustomer(Customer customer) {
        customerService.update(customer);
        return "redirect:/customer-list";
    }
    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/customer/view";
    }
}
