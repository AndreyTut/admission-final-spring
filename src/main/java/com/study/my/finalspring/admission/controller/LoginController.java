package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    private UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserTo());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistered(@Valid @ModelAttribute("user") UserTo userTo, BindingResult bindingResult,
                                 @RequestParam MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userTo);
            return "registration";
        }
        service.createFromTo(userTo);
        service.saveDiplomaImage(file, userTo.getEmail());
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
