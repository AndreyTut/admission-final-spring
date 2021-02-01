package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.model.Diploma;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.DiplomaRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DiplomaService {

    private DiplomaRepository diplomaRepository;
    private UserRepository userRepository;

    public DiplomaService(DiplomaRepository diplomaRepository, UserRepository userRepository) {
        this.diplomaRepository = diplomaRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean save(Diploma diploma, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        diploma.setUser(user);
        if (diploma.getId() == null) {
            user.setDiploma(diploma);
        }
         return diplomaRepository.save(diploma) != null;
    }
}
