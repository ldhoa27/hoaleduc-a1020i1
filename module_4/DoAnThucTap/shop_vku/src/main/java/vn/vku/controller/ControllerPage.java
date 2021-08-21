package vn.vku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.vku.repository.ProductRepository;
import vn.vku.service.CategoryService;
import vn.vku.service.ProductService;

@Controller
@RequestMapping(value = "")
public class ControllerPage {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @@RequestMapping(value = "home.html", method = RequestMethod.GET)
    public String viewHome(ModelMap modelMap){
        modelMap.put("listCategory", categoryService.getAll());
        return "pages/index";
    }
}
