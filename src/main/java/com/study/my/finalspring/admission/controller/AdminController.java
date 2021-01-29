package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students/{pagenum}")
    public String getAllStudents(Model model, @PathVariable(name = "pagenum") int pageNum) {
        Page<User> userPage = userService.getAll(pageNum);
        List<User> students = userPage.getContent();
        model.addAttribute("students", students);
        model.addAttribute("pagenum", pageNum);
        model.addAttribute("pagescount", userPage.getTotalPages());
        model.addAttribute("usersscount", userPage.getTotalElements());
        return "students";
    }

    @GetMapping("/students/view/{email}")
    public String showStudent(@PathVariable String email, Model model) {
        model.addAttribute("student", userService.getByEmail(email));
        return "viewstudent";
    }
}
