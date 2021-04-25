package ru.itis.springbootdemo.services;

import org.springframework.stereotype.Component;

public interface SmsService {
    void sendSms(String phone, String text);
}
