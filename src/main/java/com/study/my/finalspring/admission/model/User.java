package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "user_db")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 20)
    @NonNull
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String password;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String firstName;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String lastName;

    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String patronymic;

    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String city;

    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String region;

    @NotBlank
    private String schoolName;

    @OneToOne(cascade = CascadeType.ALL)
    private Diploma diploma;

    @ManyToOne
    private Faculty faculty;

    @Column(name = "enabled")
    @Builder.Default
    private boolean isEnabled = true;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<StudentMark> marks;

    @ManyToMany
    @JoinTable(name = "student_faculty", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private List<Faculty> faculties;

    @ToString.Exclude
    private byte[] diplomImage;

    private Integer status;

    @Transient
    private Integer rating;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String email) {
        this.email = email;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }
}
