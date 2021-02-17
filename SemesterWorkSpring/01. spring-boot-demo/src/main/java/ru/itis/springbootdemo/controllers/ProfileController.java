package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.services.UsersService;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile/{user-id}")
    public String getProfilePage(@PathVariable("user-id") Long userId, Model model) {
        UserDto dto = usersService.getUserById(userId);
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
