package com.study.my.epamfinalspring.controller;

import com.study.my.epamfinalspring.model.User;
import com.study.my.epamfinalspring.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        String email = principal.getName();
        User student = userService.getByEmail(email);
        model.addAttribute("student", student);
        return "editStudent";
    }
}
