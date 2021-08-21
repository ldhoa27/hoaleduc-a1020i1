//package vn.codegym.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import vn.codegym.model.Product;
//import vn.codegym.model.ProductType;
//import vn.codegym.service.LoaiSanPhamService;
//import vn.codegym.service.SanPhamService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/product")
//public class AppController {
//    @Autowired
//    private SanPhamService sanPhamService;
//
//    @Autowired
//    private LoaiSanPhamService loaiSanPhamService;
//
//    @ModelAttribute("loaiSanPhams")
//    public List<ProductType> roles(){
//        return loaiSanPhamService.findAll();
//    }
//
//    @GetMapping(value = { "/","/list","/search"})
//    public String viewAllProduct(@PageableDefault(size = 6) Pageable pageable, @RequestParam Optional<String> search,
//                                 Model model, RedirectAttributes redirectAttributes) {
//        String stringAfterCheck = "";
//        if (!search.isPresent()) {
//            model.addAttribute("productList", sanPhamService.findAllSanPham(pageable));
//        } else {
//            stringAfterCheck = search.get();
//            model.addAttribute("productList", sanPhamService.findSanPhamByNameContaining(stringAfterCheck, pageable));
//        }
//        model.addAttribute("stringAfterCheck", stringAfterCheck);
//        return "view/list";
//    }
//
//    @GetMapping("/create")
//    public ModelAndView createProduct(){
//        return new ModelAndView("view/create", "product", new Product());
//    }
//
//    @PostMapping("/create")
//    public String createProductSuccess(Model model, @Validated @ModelAttribute Product product, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            model.addAttribute(product);
//            return "view/create";
//        }
//        sanPhamService.saveSanPham(product);
//        return "redirect:/product/list";
//    }
//
//    @GetMapping("/edit/{id}")
//    public ModelAndView showEdit(@PathVariable(value = "id") int id){
//        Product product = sanPhamService.findSanPhamById(id);
//        return new ModelAndView("view/edit", "product", product);
//    }
//
//    @PostMapping("/edit")
//    public String editSuccess(@ModelAttribute Product product){
//        sanPhamService.saveSanPham(product);
//        return "redirect:/product/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public ModelAndView showDelete(@PathVariable(value = "id") int id){
//        Product product = sanPhamService.findSanPhamById(id);
//        return new ModelAndView("view/delete", "product", product);
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteSuccess(@PathVariable(value = "id") int id, Pageable pageable, Model model){
//        sanPhamService.deleteSanPhamById(id);
//        Page<Product> products = sanPhamService.findAllSanPham(pageable);
//        model.addAttribute("product", products);
//        return "redirect:/product/list";
//    }
//
//
//
//}
