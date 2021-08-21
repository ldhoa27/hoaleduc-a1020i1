package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.customer.Customer;
import vn.codegym.model.employee.Department;
import vn.codegym.model.employee.EducationDegree;
import vn.codegym.model.employee.Employee;
import vn.codegym.model.employee.Position;
import vn.codegym.service.DepartmentService;
import vn.codegym.service.EducationDegreeService;
import vn.codegym.service.EmployeeService;
import vn.codegym.service.PositionService;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EducationDegreeService educationDegreeService;


    @ModelAttribute("departments")
    public List<Department> getDepartments(){
        return departmentService.findAll();
    }

    @ModelAttribute("positions")
    public List<Position> getPositions(){
        return positionService.findAll();
    }

    @ModelAttribute("educationDegrees")
    public List<EducationDegree> getEducationDegrees(){
        return educationDegreeService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Employee employee, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "employee/create";
        }
        employeeService.save(employee);
        return "redirect:/employee/";
    }


    //delete
    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") int id){
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/employee/delete");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/delete-employee/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Pageable pageable)  {
        ModelAndView modelAndView = new ModelAndView("employee/searchTable");
        employeeService.delete(id);
        Page<Employee> employees = employeeService.findAll(pageable);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            return "employee/edit";
        }
        employeeService.save(employee);
        return "redirect:/employee/";
    }
    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "/employee/view";
    }


    @GetMapping(value = {"", "/"})
    public ModelAndView search(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        search = search.trim();
        ModelAndView modelAndView = new ModelAndView("employee/list");
        Pageable pageable = PageRequest.of(page, 5);
        if (search.equals("")) {
            modelAndView.addObject("employees", employeeService.findAll(pageable));
            return modelAndView;
        } else {
            modelAndView.addObject("search", search);
            modelAndView.addObject("employees", employeeService.findByName(search, pageable));
            return modelAndView;
        }
    }






}
