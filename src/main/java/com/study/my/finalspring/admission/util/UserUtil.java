package com.study.my.finalspring.admission.util;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public static void setUserRatingForFaculty(User user, Faculty faculty) {
        user.setRating(getMarkForSubject(user, faculty.getSubjects().get(0)) + getMarkForSubject(user, faculty.getSubjects().get(1)));
    }

    private static int getMarkForSubject(User student, Subject subject) {
        return student.getMarks()
                .stream()
                .filter(mark -> mark.getSubject().equals(subject))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getMark();
    }
}
