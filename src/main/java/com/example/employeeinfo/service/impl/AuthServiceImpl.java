package com.example.employeeinfo.service.impl;

import com.example.employeeinfo.dto.LoginDTO;
import com.example.employeeinfo.dto.RegisterDTO;
import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Employee;
import com.example.employeeinfo.exception.ResourceNotFoundException;
import com.example.employeeinfo.repository.EmployeeRepository;
import com.example.employeeinfo.repository.RoleRepository;
import com.example.employeeinfo.security.JwtTokenUtil;
import com.example.employeeinfo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final EmployeeRepository employeeRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseData registerUser(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword())) {
            ResponseData responseData = new ResponseData();
            responseData.setMessage("passwords do not match");
            responseData.setSuccess(false);
            return responseData;
        }

        if (employeeRepository.existsByUsername(registerDTO.getUsername())) {
            ResponseData responseData = new ResponseData();
            responseData.setMessage("This employee already exist");
            responseData.setSuccess(false);
            return responseData;
        }

        Employee employee = new Employee(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getEmail(),
                false,
                roleRepository.findByName(
                        "EMPLOYEE").orElseThrow(() ->
                        new ResourceNotFoundException("Role", "name", "ROLE_EMPLOYEE"))
        );
        employeeRepository.save(employee);
        ResponseData<RegisterDTO> responseData = new ResponseData<>();
        responseData.setSuccess(true);
        responseData.setMessage("You have successfully registered");

        return responseData;


    }

//    @Override
//    public ResponseData<?> loginUser(Employee employee) {
//        ResponseData<?> responseData = new ResponseData<>();
//
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    employee.getUsername(), employee.getPassword()));
//            jwtTokenUtil.generateToken(employee);
//            responseData.setMessage("Welcome");
//            responseData.setSuccess(true);
//
//        }catch (BadCredentialsException e){
//
//
//            responseData.setMessage("login or password error");
//            responseData.setSuccess(false);
//        }
//        return responseData;
//    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginDTO loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Employee employee = new Employee(loginDto.getLogin(), loginDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(jwtTokenUtil.generateToken(employee));
    }
}
