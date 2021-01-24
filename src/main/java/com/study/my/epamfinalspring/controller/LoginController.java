package com.study.my.epamfinalspring.controller;

import com.study.my.epamfinalspring.model.User;
import com.study.my.epamfinalspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistered(User user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!service.create(user)) {
            model.addAttribute("userNameError", " Username already exists");
            return "registration";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return "/login";
    }
}
