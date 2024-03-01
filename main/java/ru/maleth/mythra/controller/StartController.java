package ru.maleth.mythra.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                        @RequestParam(value = "password") String password,
                        Model model) {
        String response = userService.loginUser(name, password);
        model.addAttribute("response", response);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        return "charsheet2";
    }

    @PostMapping("{name}/register")
    public String register(@PathVariable(value = "name", required = false) String name,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password", required = false) String password,
                           Model model) {
        UserDto userDto = UserDto.builder().name(name).password(PassEncTech.encryptPass(password)).email(email).build();
        String response = userService.registerUser(userDto);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("response", response);
        return "register";
    }

}
