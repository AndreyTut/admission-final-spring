package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    private String nameEn;
    private String nameUa;
    @Min(1)
    @NotNull
    @Column(name = "vacs")
    private Integer vacancyAll;
    @Min(0)
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
