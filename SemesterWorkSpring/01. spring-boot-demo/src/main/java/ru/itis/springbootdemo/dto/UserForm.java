package ru.itis.springbootdemo.dto;

import lombok.Data;

@Data
public class UserForm {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}
