package ru.maleth.mythra.dto.mapper;

import ru.maleth.mythra.dto.UserDTO;
import ru.maleth.mythra.model.User;

public class UserMapper {

    public static User fromUserDto(UserDTO userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
