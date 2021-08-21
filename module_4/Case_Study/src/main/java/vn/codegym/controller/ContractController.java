package vn.codegym.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.contract.Contract;
import vn.codegym.model.customer.Customer;
import vn.codegym.model.employee.Employee;
import vn.codegym.model.service.AttachService;
import vn.codegym.model.service.ResortService;
import vn.codegym.service.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ResortServiceService resortServiceService;
    @Autowired
    ContractService contractService;
    @Autowired
    AttachServiceService attachServiceService;
    @Autowired
    ContractDetailService contractDetailService;

    @ModelAttribute("customer")
    public List<Customer> getAllCustomer() {
        return customerService.findAll();
    }

    @ModelAttribute("employee")
    public List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }

    @ModelAttribute("resortService")
    public List<ResortService> getAllResortService( ){
        return resortServiceService.findAll();
    }

    @ModelAttribute("attachService")
    public List<AttachService> getAllAttachService( ){
        return attachServiceService.findAll();
    }

    @GetMapping("/")
    public String ShowListContract(@RequestParam(defaultValue = "0") int page, Model model){
        Pageable pageable = PageRequest.of(page, 6);
        Page<Contract> contracts = contractService.findAll(pageable);
        model.addAttribute("contracts", contracts);
        return "contract/list";
    }

    @GetMapping("/create")
    public String ShowCreateContract(Model model){
        model.addAttribute("contract", new Contract());
        model.addAttribute("employee", new Employee());
        model.addAttribute("customer", new Customer());
        model.addAttribute("resortService", new ResortService());
        return "contract/create";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdateContract(@PathVariable int id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contracts", contract);
        return "/contract/edit";
    }

    @PostMapping("/update")
    public String updateContract(@Valid @ModelAttribute Contract contract, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contracts", contract);
            return "contract/edit";
        }
        contractService.save(contract);
        redirectAttributes.addFlashAttribute("messageUpdate", "Đã update thành công!");
        return "redirect:/contract/list";
    }

    @PostMapping("/save")
    public String SaveContract(@Valid @ModelAttribute Contract contract, BindingResult bindingResult, Model model){
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute(contract);
            return "contract/create";
        }

        contractService.save(contract);
        return "redirect:/contract/list";
    }


}
