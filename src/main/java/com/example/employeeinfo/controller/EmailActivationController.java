package com.example.employeeinfo.controller;


import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Employee;
import com.example.employeeinfo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailActivationController {

    private final EmployeeService service;

    @GetMapping("/{id}")
    ResponseData<Employee> getActivationEmployee(@PathVariable Long id){
        return service.activationEmployee(id);
    }
}
