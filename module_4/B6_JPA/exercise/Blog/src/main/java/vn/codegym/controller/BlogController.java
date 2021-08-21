package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Blog;
import vn.codegym.service.BlogService;

@Controller
@RequestMapping(value = {"blog", ""}, name = "blogController")
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showBlogList() {
        return new ModelAndView("list", "blogs",
                blogService.findAll());
    }
    @GetMapping(value = "/create")
    public String showCreatePage(Model model){
        model.addAttribute("blog", new Blog());

        return "create";
    }

    @PostMapping(value = "/create")
    public String createBlog(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMsg", "create blog success!!!: " + blog.getContent() + " OK ");
        System.out.println(blog);
        blogService.save(blog);
        return "redirect:/blog/list";
    }
    @RequestMapping(value = "/blog/{id}/edit")
    public String viewEditForm(Model model, @PathVariable int id) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "/edit";
    }

    @RequestMapping(value = "/blog/update")
    public String UpdateBlog(RedirectAttributes redirect, Blog blog) {
        blogService.update(blog);
        redirect.addFlashAttribute("success", "Update Success");
        return "redirect:/blog/list";
    }
    @GetMapping("/blog/{id}/delete")
    public String deleteBlog(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if(blog != null){
            redirectAttributes.addFlashAttribute("successMsg", "delete blog: ");
            blogService.remove(blog);
        } else{
            redirectAttributes.addFlashAttribute("success", "blog not found!!");
        }
        return "redirect:/blog/list";
    }
    @GetMapping(value = "/search")
    public ModelAndView searchBlog(@RequestParam("kq") String kq){
        return new ModelAndView("list", "blogs", blogService.searchByName(kq));
    }
}
