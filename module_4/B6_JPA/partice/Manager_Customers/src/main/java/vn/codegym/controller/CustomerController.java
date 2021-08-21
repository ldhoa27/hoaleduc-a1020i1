package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Customer;
import vn.codegym.service.CustomerService;

@Controller
@RequestMapping(value = {"customer", ""}, name = "customerController")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showCustomerList() {
        return new ModelAndView("list", "customers",
                customerService.findAll());
    }
    @GetMapping(value = "/create")
    public String showCreatePage(Model model){
        model.addAttribute("customer", new Customer());

        return "create";
    }

    @PostMapping(value = "/create")
    public String createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMsg", "create customer: "
                + customer.getLastName() + " OK");
        System.out.println(customer);
        customerService.save(customer);
        return "redirect:/customer/list";
    }
    @RequestMapping(value = "/customer/{id}/edit")
    public String viewEditForm(Model model, @PathVariable long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/edit";
    }

    @RequestMapping(value = "/customer/update")
    public String UpdateCustomer(RedirectAttributes redirect, Customer customer) {
        customerService.update(customer);
        redirect.addFlashAttribute("success", "Update Success");
        return "redirect:/customer/list";
    }
    @GetMapping("/customer/{id}/delete")
    public String deleteCustomer(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findById(id);
        if(customer != null){
            redirectAttributes.addFlashAttribute("successMsg", "delete customer: "
                    + customer.getLastName() + " OK");
            customerService.remove(customer);
        } else{
            redirectAttributes.addFlashAttribute("success", "student not found!!");
        }
        return "redirect:/customer/list";
    }
    @GetMapping(value = "/search")
    public ModelAndView searchCustomer(@RequestParam("kq") String kq){
        return new ModelAndView("list", "customers", customerService.searchByName(kq));
    }
}
