package ru.maleth.mythra.service.user.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.UserDto;
import ru.maleth.mythra.dto.UserMapper;
import ru.maleth.mythra.encrypter.PassEncTech;
import ru.maleth.mythra.model.User;
import ru.maleth.mythra.repo.UserRepo;
import ru.maleth.mythra.service.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public String registerUser(@Valid UserDto userDto) {
        if (userRepo.findByNameOrEmail(userDto.getName(), userDto.getEmail()).isPresent()) {
            return "User already exists";
        }
        userRepo.save(UserMapper.fromUserDto(userDto));
        return "Success";
    }

    @Override
    public String loginUser(String name, String inputPassword) {
        Optional<User> userOpt = userRepo.findByName(name);

        return userOpt
                .map(user -> comparePass(user.getPassword(), inputPassword))
                .orElse("Нет такого пользователя!");
    }

    private String comparePass(String userPassword, String inputPassword) {
        if (userPassword.equals(PassEncTech.encryptPass(inputPassword))) {
            System.out.println("Passwords match!");
            return "passwords match!";
        } else {
            System.out.println("Passwords DON'T match!");
            return "passwords mismatch!";
        }
    }

}
