package vn.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.Customer;
import vn.codegym.service.CustomerService;
import vn.codegym.service.Impl.CustomerServiceImpl;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList(){
        return new ModelAndView("list", "customers", customerService.finAll());

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createCustomerForm(){
        return new ModelAndView("create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createCustomer(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String country ){
        ModelAndView modelAndView = new ModelAndView("list");
        customerService.addCustomer(new Customer(id, name, email, country));
        modelAndView.addObject("customers", customerService.finAll());
        return modelAndView;

    }
}
