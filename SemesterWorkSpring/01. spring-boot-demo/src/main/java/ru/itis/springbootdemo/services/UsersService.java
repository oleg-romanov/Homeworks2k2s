package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    Boolean updateUser(Long id, UserDto newDto);
}
