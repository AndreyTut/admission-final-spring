package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.dto.FacultyMarksTo;
import com.study.my.finalspring.admission.model.*;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.SubjectRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private UserRepository userRepository;
    private FacultyService facultyService;

    private
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        facultyService = new FacultyService(facultyRepository, subjectRepository, userRepository);
    }

    @Test
    void getAll() {
        Faculty faculty = new Faculty();
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);
        when(facultyRepository.findAll()).thenReturn(faculties);
        Assertions.assertEquals(faculties, facultyService.getAll());
        verify(facultyRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        Assertions.assertTrue(facultyService.delete(1));
        verify(facultyRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void getSubjects() {
        Subject subject = new Subject();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        when(subjectRepository.findAll()).thenReturn(subjects);
        Assertions.assertEquals(facultyService.getSubjects(), subjects);
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        Faculty faculty = new Faculty();
        when(facultyRepository.findById(anyInt())).thenReturn(Optional.of(faculty));
        assertEquals(faculty, facultyService.getById(1));
        verify(facultyRepository, times(1)).findById(anyInt());
    }

    @Test
    void getExistingSubjects() {
        Subject subject = new Subject(1, "Test", "Тест");
        Faculty faculty = new Faculty();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        faculty.setSubjects(subjects);
        when(subjectRepository.findByNameEnOrNameUa(anyString(), anyString())).thenReturn(Optional.of(subject));
        Assertions.assertEquals(facultyService.getExistingSubjects(faculty), subjects);
        verify(subjectRepository, times(1)).findByNameEnOrNameUa(anyString(), anyString());
    }

    @Test
    void save() {
        Faculty faculty = new Faculty();
        when(facultyRepository.save(any())).thenReturn(faculty);
        Assertions.assertTrue(facultyService.save(faculty));
        verify(facultyRepository, times(1)).save(any());
    }

    @Test
    void getFacultyMarks() {
        User user = User.builder()
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

        Faculty faculty = new Faculty();
        FacultyMarksTo marksTo = new FacultyMarksTo();
        Subject subject = new Subject(1, "Test", "Тест");
        List<StudentMark> studentMarks = new ArrayList<>();
        StudentMark studentMark = new StudentMark(subject);
        studentMarks.add(studentMark);
        List<Subject> facultySubjects = new ArrayList<>();
        facultySubjects.add(subject);
        marksTo.setMarks(studentMarks);
        faculty.setSubjects(facultySubjects);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(facultyRepository.findById(anyInt())).thenReturn(Optional.of(faculty));
        Assertions.assertEquals(marksTo, facultyService.getFacultyMarks("email@mail.com", 1));
        verify(facultyRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).findByEmail(anyString());
    }
}