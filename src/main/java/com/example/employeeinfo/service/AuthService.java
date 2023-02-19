package com.example.employeeinfo.service;

import com.example.employeeinfo.dto.LoginDTO;
import com.example.employeeinfo.dto.RegisterDTO;
import com.example.employeeinfo.dto.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService{
    ResponseData<RegisterDTO> registerUser(RegisterDTO registerDTO);

    ResponseEntity<?> authenticateUser(LoginDTO loginDto);

}
