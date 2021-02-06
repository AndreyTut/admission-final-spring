package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.service.FacultyService;
import com.study.my.finalspring.admission.util.FacultyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/faculty")
@Slf4j
public class AdminFacultyController {

    private FacultyService facultyService;


    public AdminFacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/all")
    public String getAll(@RequestParam(required = false) String loc,
                         @RequestParam(required = false) String sortparam,
                         Model model) {
        List<Faculty> faculties = facultyService.getAll();
        faculties.sort(FacultyUtil.getComparator(sortparam, loc));
        model.addAttribute("faculties", faculties);
        return "faculties";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        facultyService.delete(id);
        return "redirect:/admin/faculty/all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Subject> list = new ArrayList<>();
        list.add(new Subject());
        list.add(new Subject());
        Faculty faculty = Faculty.builder()
                .subjects(list)
                .build();
        model.addAttribute("faculty", faculty);
        model.addAttribute("subjects", facultyService.getSubjects());
        return "addeditfaculty";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("faculty", facultyService.getById(id));
        model.addAttribute("subjects", facultyService.getSubjects());
        return "addeditfaculty";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Faculty faculty, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Binding result has errors");
            model.addAttribute("faculty", faculty);
            model.addAttribute("subjects", facultyService.getSubjects());
            return "addeditfaculty";
        }
        log.info("saving faculty before service before seting subjects: {}", faculty);
        faculty.setSubjects(facultyService.getExistingSubjects(faculty));
        log.info("saving faculty before service: {}", faculty);
        facultyService.save(faculty);
        log.info("saving faculty: {}", faculty);
        return "redirect:/admin/faculty/all";
    }
}
