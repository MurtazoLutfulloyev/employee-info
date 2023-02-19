package com.example.employeeinfo.controller;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Employee;
import com.example.employeeinfo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {


   private final  EmployeeService service;



    @PostMapping("/create")
    ResponseData<Employee> create(@RequestBody Employee employee){
        return service.create(employee);
    }


    @PatchMapping("/update")
    ResponseData<Employee> update(@RequestBody Employee employee){
        return service.update(employee);
    }

    @GetMapping("/get/{id}")
    ResponseData<Employee> getOne(@PathVariable Long id){
        return service.findOne(id);
    }

    @GetMapping("/get")
    ResponseData<List<Employee>> getAllEmployee(){
        return service.getAll();
    }
    @DeleteMapping("/delete/{id}")
    ResponseData<Employee> delete(@PathVariable Long id){
        return service.delete(id);
    }

}
