package com.study.my.finalspring.admission.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "user_db", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seo", initialValue = 1000)
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

    private String firstName;

    private String lastName;

    private String patronymic;

    private String city;

    private String region;

    private String schoolName;


    @Column(name = "enabled")
    @Builder.Default
    private boolean isEnabled = true;

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
