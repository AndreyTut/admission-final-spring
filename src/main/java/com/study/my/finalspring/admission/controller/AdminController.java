package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students/p/{pagenum}")
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
        log.info("Admin controller, view student: " + email);
        return "viewstudent";
    }

    @PostMapping("/student/{id}/changeactive")
    public String blockStudent(@PathVariable int id, @RequestParam boolean enabled, Model model) {
        User user = userService.setEnabled(id, enabled);
        return "redirect:/admin/students/view/" + user.getEmail();
    }

    @GetMapping("/student/addtoreport/{email}/{facultyId}")
    public String addStudentToReport(@PathVariable String email, @PathVariable int facultyId, Model model) {
        User user = userService.addToReport(email, facultyId);
        log.info("faculty user list: " + user.getFaculty().getStudents());
        model.addAttribute("student", user);
        return "viewstudent";
    }
}
