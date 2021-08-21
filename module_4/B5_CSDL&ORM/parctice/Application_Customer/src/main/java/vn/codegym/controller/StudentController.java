package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Student;
import vn.codegym.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"student",""}, name = "studentController")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student/{id}/edit", name = "showEditPage")
    public String showEditPage(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.finById(id));
        List<String> languageList = new ArrayList<>();
        languageList.add("JAVA");
        languageList.add("C#");
        languageList.add("PHP");

        model.addAttribute("languageList", languageList);
        return "edit";
    }

    @RequestMapping(value = "/student/update")
    public String UpdateStudent(RedirectAttributes redirect, Student student) {
        studentService.updateStudent(student);
        redirect.addFlashAttribute("success", "Update Success");
        return "redirect:/student";
    }


    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showStudentList(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        return "list";

    }

    @GetMapping(value = "/create")
    public String showCreatePage(Model model){
        model.addAttribute("student", new Student());

        List<String> languageList = new ArrayList<>();
        languageList.add("JAVA");
        languageList.add("C#");
        languageList.add("PHP");

        model.addAttribute("languageList", languageList);

        return "create";
    }

    @PostMapping(value = "/create")
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMsg", "create student: "
                + student.getName() + " OK");
        studentService.save(student);
        return "redirect:/student";

    }

    @GetMapping("/student/{id}/delete")
    public String deleteStudent(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("success", "Delete Success");
        return "redirect:/student";
    }
}
