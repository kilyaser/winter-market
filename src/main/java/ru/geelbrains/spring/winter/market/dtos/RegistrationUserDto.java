package ru.geelbrains.spring.winter.market.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
