package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.customer.Customer;
import vn.codegym.model.customer.CustomerType;
import vn.codegym.model.employee.Employee;
import vn.codegym.service.CustomerService;
import vn.codegym.service.CustomerTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTypeService customerTypeService;

    @ModelAttribute("customerTypes")
    public List<CustomerType> getCustomerTypes(){
        return customerTypeService.findAll();
    }
    //create
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        if (customerService.existById(customer.getId())) {
            bindingResult.addError(new FieldError("customer", "id", "Mã khách hàng đã tồn tại"));
        }

        if (bindingResult.hasFieldErrors()) {
            return "customer/create";
        }
        customerService.save(customer);
        return "redirect:/customer/";
    }
    //delete
    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") String id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

//    @PostMapping("/delete-customer/{id}")
//    public ModelAndView delete(@PathVariable("id") String id, Pageable pageable){
//        ModelAndView modelAndView = new ModelAndView("/customer/searchTable");
//        customerService.delete(id);
//        Page<Customer> customers = customerService.findAll(pageable);
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }

    @PostMapping("/delete-customer/{id}")
    public String delete(@PathVariable("id") String id, Pageable pageable, Model model){
        customerService.delete(id);
        Page<Customer> customers = customerService.findAll(pageable);
        model.addAttribute("customers", customers);
        return "/customer/searchTable";
    }
    //edit
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable String id, Model model){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }
//    @GetMapping("edit/{id}")
//    public String edit(@PathVariable String id, Model model) {
//        model.addAttribute("customer", customerService.findById(id));
//        return "customer/edit";
//    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "customer/edit";
        }
        customerService.save(customer);
        return "redirect:/customer/";
    }


    @GetMapping(value = {"", "/"})
    public ModelAndView search(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        search = search.trim();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        Pageable pageable = PageRequest.of(page, 6);
        if (search.equals("")) {
            modelAndView.addObject("customers", customerService.findAll(pageable));
            return modelAndView;
        } else {
            modelAndView.addObject("search", search);
            modelAndView.addObject("customers", customerService.findAllByIdOrName(search, pageable));
            return modelAndView;
        }
    }
}

