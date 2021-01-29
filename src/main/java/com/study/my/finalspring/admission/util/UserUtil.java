package com.study.my.finalspring.admission.util;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.User;

public class UserUtil {

    private UserUtil() {
    }

    public static User userFromTo(UserTo userTo) {
        User user = new User(userTo.getEmail());
        user.setEmail(userTo.getEmail());
        user.setId(userTo.getId());
        user.setPassword(userTo.getPassword());
        user.setFirstName(userTo.getFirstName());
        user.setLastName(userTo.getLastName());
        user.setPatronymic(userTo.getPatronymic());
        user.setRegion(userTo.getRegion());
        user.setCity(userTo.getCity());
        user.setSchoolName(userTo.getSchoolName());
        return user;
    }
}
