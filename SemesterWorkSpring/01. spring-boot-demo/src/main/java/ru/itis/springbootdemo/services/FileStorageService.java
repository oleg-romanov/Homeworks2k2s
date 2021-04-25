package ru.itis.springbootdemo.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    // сохраняем файл на сервере
    String saveFile(MultipartFile file, Long userId);
    // отправляем по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);
}
