package vn.codegym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandWitchController {
    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String viewIndex(){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(Model model, @RequestParam(required = false) String comdiment){
        model.addAttribute("comdiment", comdiment);
        return "/save";

    }
}
