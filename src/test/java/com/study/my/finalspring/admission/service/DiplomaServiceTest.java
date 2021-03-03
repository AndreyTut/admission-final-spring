package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.model.Diploma;
import com.study.my.finalspring.admission.model.Role;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.DiplomaRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class DiplomaServiceTest {

    @Mock
    private DiplomaRepository diplomaRepository;
    @Mock
    private UserRepository userRepository;
    private DiplomaService diplomaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        diplomaService = new DiplomaService(diplomaRepository, userRepository);
    }

    @Test
    void save() {
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

        Diploma diploma = Diploma.builder()
                .history(12)
                .chemistry(12)
                .math(12)
                .biology(12)
                .literature(12)
                .physics(12)
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(diplomaRepository.save(any())).thenReturn(diploma);

        Assertions.assertTrue(diplomaService.save(diploma, "email@mail.com"));
    }
}