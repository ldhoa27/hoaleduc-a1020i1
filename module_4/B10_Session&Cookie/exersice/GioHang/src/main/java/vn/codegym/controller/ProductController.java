package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.codegym.model.Product;
import vn.codegym.repository.ProductRepository;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller

@SessionAttributes({"listCart", "listRecommend"})
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @ModelAttribute("listCart")
    public Set<Product> listCart(){
        return new LinkedHashSet<>();
    }

    @ModelAttribute("listRecommend")
    public Set<Product> listRecommend(){
        return new LinkedHashSet<>();
    }

    @GetMapping(value = "/")
    public String home(Model model, @SessionAttribute(name = "listCart", required = false) Set<Product> listCart,
                       @SessionAttribute(name = "listRecommend", required = false) Set<Product> listRecommend){
        model.addAttribute("listProduct", productRepository.getAll());
        if (listCart == null || listRecommend == null){
            model.addAttribute("listCart", new LinkedHashSet<>());
            model.addAttribute("listRecommend", new LinkedHashSet<>());
        }else {
            model.addAttribute("listCart", new LinkedHashSet<>(listCart));
            model.addAttribute("listRecommend", new LinkedHashSet<>(listRecommend));
        }
        return "index";
    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable int id,
                       @SessionAttribute(name = "listRecommend", required = false) Set<Product> listRecommend){
        model.addAttribute("product", productRepository.findById(id));
        this.setListRecommend(listRecommend, id);
        return "view";
    }

    @GetMapping(value = "/addcart/{id}")
    public String addCart(@PathVariable int id, @SessionAttribute(name = "listCart", required = false) Set<Product> listCart){
        if (productRepository.existById(listCart, id)){
            for (Product product : listCart){
                if (product.getId() == id){
                    product.setSoLuong(product.getSoLuong()+1);
                    break;
                }
            }
        }else {
            Product product = productRepository.findById(id);
            listCart.add(new Product(product, 1));
        }
        return "redirect:/";
    }

    @GetMapping(value = "/cart")
    public String viewCart(Model model, @SessionAttribute(name = "listCart", required = false) Set<Product> listCart){
        long total=0;
        for (Product product : listCart){
            total+=(product.getGia()*product.getSoLuong());
        }
        model.addAttribute("totalMoney", total);
        model.addAttribute("finallyMoney", total-20000);
        return "cart";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable int id, @SessionAttribute(name = "listCart", required = false) Set<Product> listCart){
        listCart.removeIf(product -> product.getId()==id);
        return "redirect:/cart";
    }

    @GetMapping(value = "/deleteAll")
    public String deleteAll(@SessionAttribute(name = "listCart", required = false) Set<Product> listCart){
        listCart.clear();
        return "redirect:/";
    }

    @GetMapping(value = "/changeSL/{id}/{sl}")
    public String change(@PathVariable int id, @PathVariable int sl, @SessionAttribute(name = "listCart", required = false) Set<Product> listCart){
        for (Product product : listCart){
            if (product.getId() == id){
                product.setSoLuong(sl);
                break;
            }
        }
        return "redirect:/cart";
    }

    private void setListRecommend(Set<Product> listRecommend, int id){
        if (!productRepository.existById(listRecommend, id)){
            if (listRecommend.size() > 3){
                listRecommend.remove(productRepository.getFirstProduct(listRecommend));
            }
            Product product = productRepository.findById(id);
            if (product != null){
                listRecommend.add(new Product((product)));
            }
        }
    }


}
