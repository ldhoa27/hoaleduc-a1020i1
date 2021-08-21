package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;
import vn.codegym.model.entity.AppUser;
import vn.codegym.service.AppUserService;
import vn.codegym.service.EmployeeService;

@Controller
public class MainController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public String viewHomePage(@ModelAttribute("user") AppUser appUser) {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "403-page";
    }

}
