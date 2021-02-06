package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.Diploma;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
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
        model.addAttribute("diploma", student.getDiploma() == null ? new Diploma() : student.getDiploma());
        log.info("editing user {} with diploma {}", student, student.getDiploma());
        return "editstudent";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("student") UserTo userTo,
                         BindingResult studentBindingResult,
                         Model model) {
        User user = userService.getByEmail(userTo.getEmail());
        Diploma diploma = user.getDiploma();
        diploma = diploma != null ? diploma : new Diploma();
        if (studentBindingResult.hasErrors()) {
            model.addAttribute("student", userTo);
            model.addAttribute("diploma", diploma);
            model.addAttribute("faculties", user.getFaculties());
            return "editstudent";
        }
        log.info("updating user with id = {}", userTo.getId());
        userService.update(userTo);
        model.addAttribute("student", userTo);
        model.addAttribute("diploma", diploma);
        return "redirect:/user/edit";
    }
}
