package com.study.my.epamfinalspring.util;

import com.study.my.epamfinalspring.dto.UserTo;
import com.study.my.epamfinalspring.model.User;

public class UserUtil {

    private UserUtil() {
    }

    public static User userFromTo(UserTo userTo) {
        User user = new User(userTo.getEmail());
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
