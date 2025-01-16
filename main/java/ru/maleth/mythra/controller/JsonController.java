package ru.maleth.mythra.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.UserDto;
import ru.maleth.mythra.model.User;
import ru.maleth.mythra.repo.UserRepo;
import ru.maleth.mythra.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/json")
public class JsonController {

    private final UserService userService;
    private final UserRepo userRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String postJson(@RequestBody UserDto userDto) {
        //System.out.println(userDto);
        //User user = User.builder().email("pp@pp").name("ppp").password("password").build();
        userService.registerUser(userDto);
        Gson gson = new Gson();
        User user = userRepo.findByName(userDto.getName()).get();
        String json = gson.toJson(user);
        //System.out.println(json);
        return json;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getJson() {
        User user = userRepo.findByName("asd").get();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        //System.out.println(json);
        return json;
    }

}
