package com.example.employeeinfo.controller;

import com.example.employeeinfo.dto.LoginDTO;
import com.example.employeeinfo.dto.RegisterDTO;
import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseData<RegisterDTO> registerEmployee(@Valid @RequestBody RegisterDTO registerDTO){

        return  authService.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDto) {
        return authService.authenticateUser(loginDto);
    }


}
