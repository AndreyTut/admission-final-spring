package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.service.FacultyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminFacultyControllerTest {

    @Mock
    private FacultyService facultyService;
    private AdminFacultyController controller;
    @Mock
    Model model;
    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AdminFacultyController(facultyService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAll() throws Exception {
        List<Faculty> faculties = Mockito.mock(List.class);
        when(facultyService.getAll()).thenReturn(faculties);
        doNothing().when(faculties).sort(isA(Comparator.class));
        mockMvc.perform(get("/admin/faculty/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("faculties"))
                .andExpect(model().attributeExists("faculties"));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/admin/faculty/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/faculty/all"));
    }

    @Test
    void add() throws Exception {
        Subject subject = mock(Subject.class);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        when(facultyService.getSubjects()).thenReturn(subjects);
        mockMvc.perform(get("/admin/faculty/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addeditfaculty"))
                .andExpect(model().attributeExists("faculty", "subjects"));
    }

    @Test
    void edit() throws Exception {
        Subject subject = mock(Subject.class);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        Faculty faculty = mock(Faculty.class);
        when(facultyService.getSubjects()).thenReturn(subjects);
        when(facultyService.getById(anyInt())).thenReturn(faculty);
        mockMvc.perform(get("/admin/faculty/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("addeditfaculty"))
                .andExpect(model().attributeExists("faculty", "subjects"));
    }

    @Test
    void save() throws Exception {
        Subject subject = mock(Subject.class);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        Faculty faculty = mock(Faculty.class);
        when(facultyService.getSubjects()).thenReturn(subjects);
        when(model.getAttribute("faculty")).thenReturn(faculty);
        mockMvc.perform(post("/admin/faculty/save"))
                .andExpect(status().isOk())
                .andExpect(view().name("addeditfaculty"));
    }

    @Test
    void finalizeReport() throws Exception {
        when(facultyService.finalyzeReport(anyInt())).thenReturn(true);
        mockMvc.perform(get("/admin/faculty/1/finalize"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/faculty/all"));
    }
}