package com.study.my.epamfinalspring.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
