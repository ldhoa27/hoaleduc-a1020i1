package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import vn.codegym.model.Promotion;
import vn.codegym.service.PromotionService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("promotions", promotionService.findAll());
        return "/promotion/list";
    }

    @GetMapping("/search")
    public String index(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String searchOption, Model model) {
        if (search.equals("") || searchOption.equals("")) {
            model.addAttribute("promotions", promotionService.findAll());
        } else {
            switch (searchOption) {
                case "1":
                    try {
                        long discount = Long.parseLong(search);
                        model.addAttribute("promotions", promotionService.findByDiscount(discount));
                    } catch (Exception e) {
                        model.addAttribute("promotions", null);
                    }
                    break;
                case "2":
                    model.addAttribute("promotions", promotionService.findByStartDate(search));
                    break;
                case "3":
                    model.addAttribute("promotions", promotionService.findByEndDate(search));
                    break;
                case "4":
                    List<Promotion> result = new ArrayList<>();
                    try {
                        long discount = Long.parseLong(search);
                        result.addAll(promotionService.findByDiscount(discount));
                    } catch (Exception e) {
                        result.addAll(promotionService.findByDate(search));
                    }
                    model.addAttribute("promotions", result);
                    break;
            }
        }
        return "/promotion/list";
    }


    @GetMapping("/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "/promotion/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute Promotion promotion, BindingResult bindingResult, Model model) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (!bindingResult.hasFieldErrors("startDate")) {
            try {
                startDate = LocalDate.of(Integer.parseInt(promotion.getStartDate().substring(6, 10)), Integer.parseInt(promotion.getStartDate().substring(3, 5)), Integer.parseInt(promotion.getStartDate().substring(0, 2)));
            } catch (Exception e) {
                bindingResult.addError(new FieldError("promotion", "startDate", "Ngày nhập vào không hợp lệ"));
            }
        }

        if (!bindingResult.hasFieldErrors("endDate")) {
            try {
                endDate = LocalDate.of(Integer.parseInt(promotion.getEndDate().substring(6, 10)), Integer.parseInt(promotion.getEndDate().substring(3, 5)), Integer.parseInt(promotion.getEndDate().substring(0, 2)));
            } catch (Exception e) {
                bindingResult.addError(new FieldError("promotion", "endDate", "Ngày nhập vào không hợp lệ"));
            }
        }
        if (startDate != null && endDate != null) {
            if (ChronoUnit.DAYS.between(startDate, endDate) < 1) {
                bindingResult.addError(new FieldError("promotion", "endDate", "Ngày kết thúc phải sau ngày bắt đầu"));
            }
        }
        if (bindingResult.hasFieldErrors()) {
            return "/promotion/create";
        }
        promotionService.save(promotion);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Promotion promotion = promotionService.findById(id);
        ModelAndView modelAndView = new ModelAndView("promotion/edit");
        modelAndView.addObject("promotion", promotion);
        modelAndView.addObject("promotions", promotionService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute Promotion promotion, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "promotion/edit";
        }
        promotionService.save(promotion);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") int id){
        Promotion promotion = promotionService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/promotion/delete");
        modelAndView.addObject("promotion", promotion);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("/promotion/searchTable");
        promotionService.delete(id);
        List<Promotion> promotions = promotionService.findAll();
        modelAndView.addObject("promotions", promotions);
        return modelAndView;
    }


}
