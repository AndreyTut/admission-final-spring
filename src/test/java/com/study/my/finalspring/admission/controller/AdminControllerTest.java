package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminControllerTest {

    private AdminController adminController;
    @Mock
    private UserService userService;
    @Mock
    Model model;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adminController = new AdminController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void getAllStudents() throws Exception {
        Page<User> page = mock(Page.class);
        when(userService.getAll(anyInt())).thenReturn(page);
        mockMvc.perform(get("/admin/students/p/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students", "pagenum", "pagescount", "usersscount"));
    }

    @Test
    void showStudent() throws Exception {
        User user = mock(User.class);
        when(userService.getByEmail(anyString())).thenReturn(user);
        mockMvc.perform(get("/admin/students/view/test@email.ua"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewstudent"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void blockStudent() throws Exception {
        User user = mock(User.class);
        when(userService.setEnabled(1, true)).thenReturn(user);
        when(user.getEmail()).thenReturn("test@email.ua");

        mockMvc.perform(post("/admin/student/1/changeactive?enabled=true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/students/view/test@email.ua"));
    }

    @Test
    void addStudentToReport() throws Exception {
        User user = mock(User.class);
        Faculty faculty = mock(Faculty.class);
        when(userService.addToReport(anyString(), anyInt())).thenReturn(user);
        when(user.getFaculty()).thenReturn(faculty);
        when(faculty.getSubjects()).thenReturn(null);
        mockMvc.perform(get("/admin/student/addtoreport/test@email.ua/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewstudent"))
                .andExpect(model().attributeExists("student"));
    }
}