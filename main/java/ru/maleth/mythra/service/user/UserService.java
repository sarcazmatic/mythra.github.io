package ru.maleth.mythra.service.user;

import org.springframework.ui.Model;
import ru.maleth.mythra.dto.UserDto;

import java.util.Map;

public interface UserService {

    Map<String, String> loginUser(String login, String inputPassword);

    String registerUser(UserDto userDto);

}
