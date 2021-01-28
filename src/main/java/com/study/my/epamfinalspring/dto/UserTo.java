package com.study.my.epamfinalspring.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserTo {

    private Integer id;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 20)
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|[A-Z][a-z']+")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)")
    private String lastName;
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)|''")
    private String patronymic;
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)")
    private String city;
    @Pattern(regexp = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)")
    private String region;
    @NotBlank
    private String schoolName;
}
