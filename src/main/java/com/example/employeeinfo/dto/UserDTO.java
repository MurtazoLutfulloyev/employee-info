package com.example.employeeinfo.dto;

import lombok.Data;
import com.example.employeeinfo.entity.enums.RoleType;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;
    private Set<RoleType> roles;

}
