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

    public static User prepareToUpdate(UserTo userTo, User old) {
        User user = userFromTo(userTo);
        user.setRoles(old.getRoles());
        user.setDiploma(old.getDiploma());
        user.setDiplomImage(old.getDiplomImage());
        user.setFaculties(old.getFaculties());
        return user;
    }
}
