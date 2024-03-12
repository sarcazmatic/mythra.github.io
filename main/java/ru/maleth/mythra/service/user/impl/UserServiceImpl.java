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
    public String loginUser(String name, String inputPassword) {
        Optional<User> user = userRepo.findByName(name);

        return user.map(u -> comparePass(u.getPassword(), inputPassword)).orElse("user_error");

    }

    @Override
    public String registerUser(@Valid UserDto userDto) {
        if (userRepo.findByNameOrEmail(userDto.getName(), userDto.getEmail()).isPresent()) {
            return "user_error";
        }
        userRepo.save(UserMapper.fromUserDto(userDto));
        return "register";
    }

    private String comparePass(String userPassword, String inputPassword) {
        if (userPassword.equals(PassEncTech.encryptPass(inputPassword))) {
            return "login";
        } else {
            return "pass_error";
        }
    }

}
