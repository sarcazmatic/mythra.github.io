package ru.maleth.mythra.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.maleth.mythra.dto.UserDto;
import ru.maleth.mythra.encrypter.PassEncTech;
import ru.maleth.mythra.service.user.UserService;

@Controller
@Data
public class StartController {

    private final UserService userService;

    @GetMapping({"/", "/index"})
    public String hello() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    @PostMapping("{name}/register")
    public String register(@PathVariable("name") String name,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password) {
        UserDto userDto = UserDto.builder()
                .name(name)
                .password(PassEncTech.encryptPass(password))
                .email(email)
                .build();
        return userService.registerUser(userDto);
    }

}
