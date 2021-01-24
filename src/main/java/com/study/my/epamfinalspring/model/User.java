package com.study.my.epamfinalspring.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String city;

    private String region;

    private Map<String, Integer> marks = new HashMap<>();

    private int diplomAvarageMark;

    private Byte[] diplomImage;

    private String password;

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
