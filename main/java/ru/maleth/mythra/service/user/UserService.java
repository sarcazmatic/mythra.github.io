package ru.maleth.mythra.service.user;

import ru.maleth.mythra.dto.UserDto;

public interface UserService {

    String registerUser(UserDto userDto);

    String loginUser(String name, String password);

}
