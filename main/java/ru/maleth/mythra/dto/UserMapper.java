package ru.maleth.mythra.dto;

import ru.maleth.mythra.model.User;

public class UserMapper {

    public static User fromUserDto(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
