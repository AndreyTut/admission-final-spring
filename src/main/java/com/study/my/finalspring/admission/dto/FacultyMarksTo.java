package com.study.my.finalspring.admission.dto;

import com.study.my.finalspring.admission.model.StudentMark;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class FacultyMarksTo {
    private List<StudentMark> marks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyMarksTo)) return false;
        FacultyMarksTo that = (FacultyMarksTo) o;
        return Objects.equals(getMarks(), that.getMarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarks());
    }
}
