package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Role;
import com.study.my.finalspring.admission.model.StudentMark;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.StudentMarkRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import com.study.my.finalspring.admission.util.exceptions.NotFoundException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    private User testUser;
    private UserTo testUserTo;

    @Mock
    private StudentMarkRepository studentMarkRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, studentMarkRepository, facultyRepository, passwordEncoder);
        testUser = User.builder()
                .email("test@mail.com")
                .firstName("Test")
                .lastName("Test")
                .patronymic("Test")
                .city("Test")
                .password("1")
                .region("Test")
                .schoolName("Test")
                .roles(Collections.singleton(Role.ROLE_USER))
                .build();

        testUserTo = new UserTo();
        testUserTo.setEmail(testUser.getEmail());
        testUserTo.setPassword(testUser.getPassword());
        testUserTo.setFirstName(testUser.getFirstName());
        testUserTo.setLastName(testUser.getLastName());
        testUserTo.setPassword("1");
        testUserTo.setPatronymic(testUser.getPatronymic());
        testUserTo.setCity(testUser.getCity());
        testUserTo.setRegion(testUser.getRegion());
        testUserTo.setSchoolName("test");
    }

    @Test
    void getAll() {
        List<User> list = new ArrayList<>();
        list.add(testUser);
        when(userRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(1, userService.getAll().size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getByEmail() {
        String email = "test@mail.com";
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        User actual = userService.getByEmail(email);
        Assertions.assertEquals(testUser, actual);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void getByEmailNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userService.getByEmail("fakeEmail@fake.com"));
    }

    @Test
    void create() {
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertTrue(userService.create(testUser));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void createExisted() {
        testUser.setId(1);
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertFalse(userService.create(testUser));
    }

    @Test
    void update() {
        testUser.setId(1);
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertTrue(userService.update(testUser));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void updateNotExisted() {
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertFalse(userService.update(testUser));
    }

    @Test
    void createFromTo() {
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertTrue(userService.createFromTo(testUserTo));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @Ignore
    void updateFromTo() {
        testUserTo.setId(1);
        when(userRepository.findById(any())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);
        Assertions.assertTrue(userService.update(testUserTo));
        verify(userRepository, times(1)).findById(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void setEnabled() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        Assertions.assertEquals(testUser, userService.setEnabled(1, true));
        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void saveMarks() {
        List<StudentMark> marks = new ArrayList<>();
        marks.add(new StudentMark());
        marks.add(new StudentMark());
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        Assertions.assertTrue(userService.saveMarks(marks, testUser.getEmail()));
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(studentMarkRepository, times(1)).saveAll(any());
    }

    @Test
    void addFaculty() {
        Faculty faculty = new Faculty();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(facultyRepository.findById(anyInt())).thenReturn(Optional.of(faculty));
        Assertions.assertTrue(userService.addFaculty(1, testUser.getEmail()));
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(facultyRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any());
    }


    @Test
    void addToReport() {
        Faculty faculty = new Faculty();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(facultyRepository.findById(anyInt())).thenReturn(Optional.of(faculty));
        Assertions.assertEquals(testUser, userService.addToReport(testUser.getEmail(), 1));
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(facultyRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any());
    }
}