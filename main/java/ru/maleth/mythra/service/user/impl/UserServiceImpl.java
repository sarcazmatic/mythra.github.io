package ru.maleth.mythra.service.user.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.UserDTO;
import ru.maleth.mythra.dto.mapper.UserMapper;
import ru.maleth.mythra.encrypter.PassEncTech;
import ru.maleth.mythra.model.User;
import ru.maleth.mythra.repo.UserRepo;
import ru.maleth.mythra.service.user.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public Map<String, String> loginUser(String login, String inputPassword) {
        Optional<User> user = userRepo.findByName(login);

        Map<String, String> attributes = new HashMap<>();
        attributes.put("login", login);
        attributes.put("inputField", inputPassword);
        attributes.put("directToPage", "test");
        return attributes;

        /*
        return user.map(u
                -> comparePass(u.getPassword(), inputPassword)).orElse("user_error");
        */

    }

    @Override
    public String registerUser(@Valid UserDTO userDto) {
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
