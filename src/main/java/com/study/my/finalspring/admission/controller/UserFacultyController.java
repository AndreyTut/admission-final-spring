package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.dto.FacultyMarksTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.service.FacultyService;
import com.study.my.finalspring.admission.service.UserService;
import com.study.my.finalspring.admission.util.FacultyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/faculty")
public class UserFacultyController {

    private FacultyService facultyService;
    private UserService userService;

    public UserFacultyController(FacultyService facultyService, UserService userService) {
        this.facultyService = facultyService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String add(@RequestParam(required = false) String loc,
                      @RequestParam(required = false) String sortparam,
                      Model model) {
        List<Faculty> faculties = facultyService.getAll();
        faculties.sort(FacultyUtil.getComparator(sortparam, loc));
        model.addAttribute("faculties", faculties);
        return "studentfaculties";
    }

    @GetMapping("/{id}/submit")
    public String submit(@PathVariable int id, Model model, Principal principal) {
        String email = principal.getName();
        FacultyMarksTo facultyMarks = facultyService.getFacultyMarks(email, id);
        userService.addFaculty(id, email);
        model.addAttribute(facultyMarks);
        return "submitfaculty";
    }

    @PostMapping("/submit")
    public String getMarks(@ModelAttribute FacultyMarksTo marks, Principal principal) {
        userService.saveMarks(marks.getMarks(), principal.getName());
        return "redirect:/user/edit";
    }
}
