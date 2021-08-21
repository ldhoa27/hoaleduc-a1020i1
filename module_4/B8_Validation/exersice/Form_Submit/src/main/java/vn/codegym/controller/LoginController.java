package vn.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.codegym.model.Login;

import javax.validation.Valid;
@Controller
public class LoginController {
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("login", new Login());
        return "index";
    }
    @PostMapping("/")
    public String checkValidation (@Valid @ModelAttribute("login")Login login, BindingResult bindingResult, Model model){
        new Login().validate(login, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "index";
        }
        else {
            model.addAttribute("login", login);
            return "result";
        }
    }
}
