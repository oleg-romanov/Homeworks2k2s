package ru.itis.springbootdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserForm;
import ru.itis.springbootdemo.models.Image;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.services.MailsService;
import ru.itis.springbootdemo.services.SignUpService;
import ru.itis.springbootdemo.services.SmsService;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MailsService mailsService;

    @Override
    public void signUp(UserForm form) {
        User newUser = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .phone(form.getPhone())
                .state(State.NOT_CONFIRMED)
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .confirmCode(UUID.randomUUID().toString())
                .imageName("")
                .isAdmin(false)
                .build();

        usersRepository.save(newUser);
        mailsService.sendEmailForConfirm(newUser.getEmail(), newUser.getConfirmCode());
        smsService.sendSms(form.getPhone(), "Поздравляем! Вы успешно зарегестрированы!");
    }
}
