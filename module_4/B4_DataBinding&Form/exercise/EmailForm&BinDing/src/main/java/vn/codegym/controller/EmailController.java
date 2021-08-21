package vn.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.codegym.model.Email;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmailController {

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String showEmailList(Model model){
      model.addAttribute("email", new Email());
        List<Integer> pageSizes = new ArrayList<>();
        pageSizes.add(10);
        pageSizes.add(20);
        pageSizes.add(30);
        List<String> languages = new ArrayList<>();
        languages.add("JAVA");
        languages.add("PHP");
        languages.add("PYTHON");

        model.addAttribute("pageSizes", pageSizes);
        model.addAttribute("languages", languages);

        return "create";
    }
    @RequestMapping("/create")
    public String result(@ModelAttribute Email email, Model model){
        model.addAttribute("pageSizes", email.getPageSize());
        model.addAttribute("languages", email.getLanguage());
        model.addAttribute("spamlillters", email.isSpamFillter());
        model.addAttribute("signature", email.getSignature());
        
        return "result";

    }
}
