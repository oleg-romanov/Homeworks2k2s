package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.models.User;

import java.util.Optional;

public interface MailsService {
    void sendEmailForConfirm(String email, String code);
}
