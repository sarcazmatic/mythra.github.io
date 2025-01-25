package ru.maleth.mythra.service.user;

import ru.maleth.mythra.dto.UserDTO;

import java.util.Map;

public interface UserService {

    Map<String, String> loginUser(String login, String inputPassword);

    String registerUser(UserDTO userDto);

}
