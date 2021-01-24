package com.study.my.epamfinalspring.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "user_db", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seo", initialValue = 1000)
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 20)
    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String city;

    private String region;

//    private Map<String, Integer> marks = new HashMap<>();

    @Min(1)
    @Max(12)
    private Integer diplomAvarageMark;

    private Byte[] diplomImage;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

//    public Map<String, Integer> getMarks() {
//        return marks;
//    }
//
//    public void setMarks(Map<String, Integer> marks) {
//        this.marks = marks;
//    }

    public int getDiplomAvarageMark() {
        return diplomAvarageMark;
    }

    public void setDiplomAvarageMark(int diplomAvarageMark) {
        this.diplomAvarageMark = diplomAvarageMark;
    }

    public Byte[] getDiplomImage() {
        return diplomImage;
    }

    public void setDiplomImage(Byte[] diplomImage) {
        this.diplomImage = diplomImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles = roles == null ? new HashSet<>() : roles;
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", diplomAvarageMark=" + diplomAvarageMark +
                ", diplomImage=" + Arrays.toString(diplomImage) +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }
}
