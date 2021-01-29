package com.study.my.finalspring.admission;

import com.study.my.finalspring.admission.model.Role;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class AdmissionFinalSpringApplication implements CommandLineRunner {

    @Autowired
    UserService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        SpringApplication.run(AdmissionFinalSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = service.getByEmail("user@ukr.net");
//        logger.info(user.toString());
//        logger.info(user.getEmail());
//        logger.info(user.getPassword());
//        User user1 = new User("us@muil.com");
//        user1.setPassword("qwerty");
//        user1.addRole(Role.ROLE_USER);
//        User user2 = new User("ua_user@mail.com");
//        user2.setPassword("qwerty");
//        service.create(user1);
//        service.create(user2);
        service.create(User.builder().city("Dnipro").lastName("Petrenko").firstName("Ivan").patronymic("Григорович").email("user13@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user14@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user15@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user16@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user17@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user18@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user19@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user20@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user21@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user22@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user23@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user24@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user25@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("use26@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user27@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user28@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user29@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user30@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user31@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user110@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user220@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user5@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user6@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user7@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user8@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user9@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user0@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user1@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user32@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user3@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user234@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user53@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("use263@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user275@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user282@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user291@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user304@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
        service.create(User.builder().email("user310@user.com").password("1").roles(Collections.singleton(Role.ROLE_USER)).build());
    }
}
