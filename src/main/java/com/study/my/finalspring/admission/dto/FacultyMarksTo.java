package com.study.my.finalspring.admission.dto;

import com.study.my.finalspring.admission.model.StudentMark;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FacultyMarksTo {
    private List<StudentMark> marks = new ArrayList<>();
}
