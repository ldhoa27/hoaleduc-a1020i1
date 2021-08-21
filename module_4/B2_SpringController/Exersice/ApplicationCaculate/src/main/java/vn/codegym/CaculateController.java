package vn.codegym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaculateController {
    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String viewCaculate(){
        return "index";
    }
    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    public String Calculate(@RequestParam("first") int first, @RequestParam("second") int second, @RequestParam("operator") String operator, Model model){
        int result = 0;
        switch (operator){
            case "+":
                result = first + second;
                break;
            case "-":
                result =  first - second;
                break;
            case "*":
                result =  first * second;
                break;
            case "/":
                if(second != 0)
                    result =   first / second;
                else
                    throw new RuntimeException("Can't divide by zero");
                break;
            default:
                throw new RuntimeException("Invalid operation");
        }
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("result", result);
        return "index";

    }
}
