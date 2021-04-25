package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.UsersService;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        UserDto dto = usersService.getUserDto(email);
        model.addAttribute("userDto", dto);
        return "profile";
    }

    @PutMapping("/profile/{user-id}")
    @ResponseBody
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto, @PathVariable("user-id") Long userId) {
        boolean isChanged = usersService.updateUser(userId, dto);
        //Планировалось поработать со статусными кодами в зависимости от 'isChanged', но как пока не знаю...
        UserDto newDto = usersService.getUserById(userId);
        return ResponseEntity.ok(newDto);
    }


}
