package com.teamvietdev.controller;

import com.teamvietdev.model.Product;
import com.teamvietdev.service.CategoryService;
import com.teamvietdev.service.ProductService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TVD
 */
@Controller
@RequestMapping(value = "manages/product")
public class ControllerProductManage {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String viewProductList(ModelMap mm, HttpSession session) {
        mm.put("listProduct", productService.getListNav(0, 10));
        mm.put("totalItem", productService.totalItem() / 10);
        return "manages/product_list";
    }

    @RequestMapping(value = "list/{page}.html", method = RequestMethod.GET)
    public String viewProductListByPage(ModelMap mm, HttpSession session, @PathVariable("page") int page) {
        mm.put("listProduct", productService.getListNav((page - 1) * 10, 10));
        mm.put("totalItem", productService.totalItem() / 10);
        return "manages/product_list";
    }

    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String viewProductNew(ModelMap mm, HttpSession session) {
        mm.put("product", new Product());
        mm.put("listCategory", categoryService.getAll());
        return "manages/product_form";
    }

    @RequestMapping(value = "edit/{productId}.html", method = RequestMethod.GET)
    public String viewProductEdit(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {
        Product p = productService.findById(productId);
        mm.put("product", p);
        mm.put("listCategory", categoryService.getAll());
        return "manages/product_form";
    }

    @RequestMapping(value = "save.html", method = RequestMethod.POST)
    public String viewProductSave(ModelMap mm, HttpSession session, @ModelAttribute("product") Product product) {
        if (product.getProductId() == 0) {
            productService.create(product);
        } else {
            productService.update(product);
        }
        mm.put("product", product);
        mm.put("listCategory", categoryService.getAll());
        return "manages/product_form";
    }

    @RequestMapping(value = "remove/{productId}.html", method = RequestMethod.GET)
    public String viewProductRemove(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {
        Product p = productService.findById(productId);
        if (p != null) {
            productService.delete(p);
        }
        mm.put("listProduct", productService.getListNav(0, 10));
        mm.put("totalItem", productService.totalItem() / 10);
        return "manages/product_list";
    }

}
