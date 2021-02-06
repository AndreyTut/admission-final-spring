package com.study.my.finalspring.admission.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Subject subject;
    @Min(0)
    @Max(200)
    private Integer mark;

    public StudentMark(Subject subject) {
        this.subject = subject;
    }
}
