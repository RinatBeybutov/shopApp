package com.petProject.UserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private String name;

    private LocalDate registeredAt;

    private String email;
}
