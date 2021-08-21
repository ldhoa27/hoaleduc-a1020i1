package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.service.RentType;
import vn.codegym.model.service.ResortService;
import vn.codegym.model.service.ServiceType;
import vn.codegym.service.RentTypeService;
import vn.codegym.service.ResortServiceService;
import vn.codegym.service.ServiceTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ResortServiceController {
    @Autowired
    private ResortServiceService resortServiceService;

    @Autowired
    private RentTypeService rentTypeService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @ModelAttribute("rentTypes")
    public List<RentType> getRentTypeList(){
        return rentTypeService.findAll();
    }

    @ModelAttribute("serviceTypes")
    public List<ServiceType> getServiceTypeList(){
        return serviceTypeService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("resort_services/create");
        modelAndView.addObject("resortService", new ResortService());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute ResortService resortService, BindingResult bindingResult, Model model) {
        if (resortServiceService.existById(resortService.getId())) {
            bindingResult.addError(new FieldError("resortService", "id", "Mã dịch vụ đã tồn tại"));
        }

        if (bindingResult.hasFieldErrors()) {
            return "resort_services/create";
        }
        resortServiceService.save(resortService);
        return "redirect:/service/";
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView search(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        search = search.trim();
        ModelAndView modelAndView = new ModelAndView("resort_services/list");
        Pageable pageable = PageRequest.of(page, 6);
        if (search.equals("")) {
            modelAndView.addObject("services", resortServiceService.findAll(pageable));
        } else {
            modelAndView.addObject("search", search);
            modelAndView.addObject("services", resortServiceService.findByName(search, pageable));
        }
        return modelAndView;
    }

}
