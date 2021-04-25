package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    Boolean updateUser(Long id, UserDto newDto);

    Boolean setUserConfirmed(String confirmCode);

    UserDto getUserDto(String email);
    UserDto getUserDto(Long id);
}
