package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "[A-Za-z\\s]+")
    private String nameEn;
    @Pattern(regexp = "[А-Яа-яіїщ'єІЇЄЩ\\s]+")
    private String nameUa;
    @Min(1)
    @Max(1000)
    @NotNull
    @Column(name = "vacs")
    private Integer vacancyBudge;
    @Min(0)
    @Max(1000)
    @NotNull
    @Column(name = "vacs_contr")
    private Integer vacancyContr;
    @ManyToMany
    @JoinTable(name = "faculty_subject", joinColumns = @JoinColumn(name = "faculty_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
    @ManyToMany(mappedBy = "faculties")
    @ToString.Exclude
    private List<User> students;
}
