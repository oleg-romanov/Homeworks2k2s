package ru.itis.springbootdemo.controllers;

import javassist.NotFoundException;
import org.springframework.ui.Model;
import ru.itis.springbootdemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.services.UsersService;

@Controller
public class ConfirmController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code) throws NotFoundException {
        System.out.println(code);
        Boolean isConfirmed;
        if (usersService.setUserConfirmed(code)) {
            isConfirmed = true;
        }

        return "redirect:/profile";
    }
}
