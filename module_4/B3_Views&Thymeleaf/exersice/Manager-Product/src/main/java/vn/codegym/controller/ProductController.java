package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Product;
import vn.codegym.service.ProductServiceImpl;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("products", productService.findALl());
        return "/index";
    }

    @RequestMapping(value = "/product/{id}/edit")
    public String viewEditForm(Model model, @PathVariable int id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "/edit";
    }

    @RequestMapping(value = "/product/update")
    public String UpdateProduct(RedirectAttributes redirect, Product product) {
        productService.updateProduct(product);
        redirect.addFlashAttribute("success", "Update Success");
        return "redirect:/product";
    }

    @GetMapping("/product/create")
    public String viewFormCreate(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/product/create")
    public String saveProduct(Product product, RedirectAttributes redirect) {
        productService.saveProduct(product);
        redirect.addFlashAttribute("success", "Success Create");
        return "redirect:/product";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("success", "Delete Success");
        return "redirect:/product";
    }


}
