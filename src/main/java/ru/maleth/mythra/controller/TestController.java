package ru.maleth.mythra.controller;

import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.UserDTO;
import ru.maleth.mythra.model.User;
import ru.maleth.mythra.repo.UserRepo;
import ru.maleth.mythra.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@Data
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String get(Model model) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("directToPage", "test");
        model.addAllAttributes(attributes);
        return attributes.get("directToPage");
    }

}
