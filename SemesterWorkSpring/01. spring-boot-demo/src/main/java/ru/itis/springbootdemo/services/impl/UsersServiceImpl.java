package ru.itis.springbootdemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.services.UsersService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static ru.itis.springbootdemo.dto.UserDto.*;

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
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!newDto.getEmail().equals("")) {
                user.setEmail(newDto.getEmail());
                isChanged = true;
            }
            if (!newDto.getFirstName().equals("")) {
                user.setFirstName(newDto.getFirstName());
                isChanged = true;
            }
            if (!newDto.getLastName().equals("")) {
                user.setLastName(newDto.getLastName());
                isChanged = true;
            }
            if (newDto.getImageName() != null && !newDto.getImageName().equals("")) {
                user.setImageName(newDto.getImageName());
                isChanged = true;
            }
            if (isChanged) {
                usersRepository.save(user);
            }
        }
        return isChanged;
    }

    @Override
    public Boolean setUserConfirmed(String confirmCode) {
        boolean isConfirmed = false;
        Optional<User> user = usersRepository.findByConfirmCode(confirmCode);
        if (user.isPresent()) {
            user.get().setState(State.CONFIRMED);
            usersRepository.save(user.get());
            isConfirmed = true;
        }
        return  isConfirmed;
    }

    @Override
    public UserDto getUserDto(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        UserDto dto = UserDto.builder().build();

        if (user.isPresent()) {
            dto = builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .imageName(user.get().getImageName())
                    .build();
        }
        return dto;
    }

    public UserDto getUserDto(Long id) {
        Optional<User> user = usersRepository.findById(id);
        UserDto dto = UserDto.builder().build();

        if (user.isPresent()) {
            dto = builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .imageName(user.get().getImageName())
                    .build();
        }
        return dto;
    }
}
