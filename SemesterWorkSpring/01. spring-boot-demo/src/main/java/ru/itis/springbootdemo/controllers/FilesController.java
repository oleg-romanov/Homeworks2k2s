package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.services.FileStorageService;
import ru.itis.springbootdemo.services.UsersService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FilesController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/{userId}/files")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long userId) {
        String fileName = fileStorageService.saveFile(file, userId);
        UserDto userDto = usersService.getUserById(userId);
        userDto.setImageName(fileName);
        usersService.updateUser(userId, userDto);
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping("/files/{file-name:.+}")
    @CrossOrigin(origins = "http://localhost:80")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        fileStorageService.writeFileToResponse(fileName, response);
    }

}
