package com.example.employeeinfo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    @Size(min = 1, max = 50)
    private String login;

    @NotBlank
//    @Size(min = 4, max = 100)
    private String password;

}
