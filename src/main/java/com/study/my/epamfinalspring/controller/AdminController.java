package com.study.my.epamfinalspring.controller;

import com.study.my.epamfinalspring.model.User;
import com.study.my.epamfinalspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<User> students = userService.getAll();
        model.addAttribute("students", students);
        return "students";
    }
}
