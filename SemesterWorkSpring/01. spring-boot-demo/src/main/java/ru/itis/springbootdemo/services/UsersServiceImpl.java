package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static ru.itis.springbootdemo.dto.UserDto.*;
/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return UserDto.from(user.orElse(User.builder().build()));
    }

    @Override
    public Boolean updateUser(Long id, UserDto newDto) {
        boolean isChanged = false;
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()) {
            if (!newDto.getEmail().equals("")) {
                user.get().setEmail(newDto.getEmail());
                isChanged = true;
            }
            if (!newDto.getFirstName().equals("")) {
                user.get().setFirstName(newDto.getFirstName());
                isChanged = true;
            }
            if (!newDto.getLastName().equals("")) {
                user.get().setLastName(newDto.getLastName());
                isChanged = true;
            }
        }
        if (isChanged) {
            usersRepository.save(user.get());
        }
        return isChanged;
    }
}
