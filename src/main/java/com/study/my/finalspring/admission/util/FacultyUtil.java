package com.study.my.finalspring.admission.util;

import com.study.my.finalspring.admission.model.Faculty;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

@Slf4j
public class FacultyUtil {



    //TODO change exception type on my own
    public static Comparator<Faculty> getComparator(String sortBy, String locale) {
        if (sortBy == null) {
            return Comparator.comparing(Faculty::getNameEn);
        }
        switch (sortBy) {
            case "name":
                return locale.equals("en")
                        ? Comparator.comparing(Faculty::getNameEn)
                        : Comparator.comparing(Faculty::getNameUa);
            case "budg":
                return Comparator.comparing(Faculty::getVacancyBudge);
            case "contr":
                return Comparator.comparing(Faculty::getVacancyContr);
        }
        throw new RuntimeException("Illegal sorting parameters");
    }
}
