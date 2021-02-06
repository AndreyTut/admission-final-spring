package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.Diploma;
import com.study.my.finalspring.admission.service.DiplomaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class DiplomaController {

    private DiplomaService diplomaService;

    public DiplomaController(DiplomaService diplomaService) {
        this.diplomaService = diplomaService;
    }

    @PostMapping("/user/diploma/update")
    public String save(@Valid @ModelAttribute("diploma") Diploma diploma, BindingResult diplomaBindingResult,
                       @ModelAttribute("student") UserTo userTo,
                       Model model, Principal principal) {
        if (diplomaBindingResult.hasErrors()) {
            log.info("binding results have errors");
            model.addAttribute("student", userTo);
            model.addAttribute("diploma", diploma);
            return "editstudent";
        }
        String email = principal.getName();
        log.info("saving diploma {} for student with email {}", diploma, email);
        diplomaService.save(diploma, email);
        return "redirect:/user/edit";
    }
}
