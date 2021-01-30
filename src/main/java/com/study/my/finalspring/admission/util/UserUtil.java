package com.study.my.finalspring.admission.util;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.User;

public class UserUtil {

    private UserUtil() {
    }

    public static User userFromTo(UserTo userTo) {
        return User.builder()
                .email(userTo.getEmail())
                .password(userTo.getPassword())
                .id(userTo.getId())
                .firstName(userTo.getFirstName())
                .lastName(userTo.getLastName())
                .patronymic(userTo.getPatronymic())
                .region(userTo.getRegion())
                .city(userTo.getCity())
                .schoolName(userTo.getSchoolName())
                .build();
    }
}
