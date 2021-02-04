package com.study.my.finalspring.admission;

import com.study.my.finalspring.admission.model.*;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.SubjectRepository;
import com.study.my.finalspring.admission.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class AdmissionFinalSpringApplication implements CommandLineRunner {

    @Autowired
    UserService service;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @PersistenceContext
    @Autowired
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        SpringApplication.run(AdmissionFinalSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = service.getByEmail("user@ukr.net");
        Diploma diploma = Diploma.builder()
                .math(10)
                .chemistry(11)
                .history(12)
                .literature(10)
                .physics(9)
                .biology(8)
                .build();

        user.setDiploma(diploma);
        user.setCity("Жашків");
        user.setRegion("Київська");
        user.setFirstName("Дмитро");
        user.setPatronymic("Станіславович");
        user.setLastName("Проскурін");
        user.setSchoolName("Ліцей №4");
        diploma.setUser(user);
        service.update(user);

        User user1 = User.builder()
                .lastName("Гончар")
                .firstName("Іван")
                .patronymic("Федорович")
                .city("Бровари")
                .email("us@us.net")
                .region("Kyiv")
                .schoolName("#5")
                .password("1")
                .build();
        service.update(user);
        service.create(user1);

        logger.info("try find mathematic -> {}",subjectRepository.findByNameEnOrNameUa("Математика", "Математика").orElse(null));

    }


}
