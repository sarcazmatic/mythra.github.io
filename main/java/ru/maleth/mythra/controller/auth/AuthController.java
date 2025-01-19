package ru.maleth.mythra.controller.auth;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.UserDto;
import ru.maleth.mythra.encrypter.PassEncTech;
import ru.maleth.mythra.service.user.UserService;

@Controller
@Data
public class AuthController {

    private final UserService userService;

    @GetMapping({"/", "/index"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String hello() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "login") String login,
                        @RequestParam(value = "password ") String inputPassword,
                        Model model) {
        model.addAllAttributes(userService.loginUser(login, inputPassword));
        return model.getAttribute("directToPage").toString();
    }

    @PostMapping("{name}/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(UserDto userDto) {
        userDto.setPassword(PassEncTech.encryptPass(userDto.getPassword()));
        return userService.registerUser(userDto);
    }

}
