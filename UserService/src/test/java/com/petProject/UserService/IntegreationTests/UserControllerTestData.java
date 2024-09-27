package com.petProject.UserService.IntegreationTests;

import com.petProject.UserService.dto.UserDto;

import java.time.LocalDate;

public class UserControllerTestData {

    public static UserDto getViewUserDto() {
        UserDto userDto = new UserDto();
        userDto.setEmail("abc@mail.ru");
        userDto.setName("Gaben");
        userDto.setRegisteredAt(LocalDate.parse("2024-09-19"));
        return userDto;
    }
}
