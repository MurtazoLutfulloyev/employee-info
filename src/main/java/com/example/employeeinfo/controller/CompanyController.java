package com.example.employeeinfo.controller;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Company;
import com.example.employeeinfo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;


    @PostMapping("/create")
    ResponseData<Company> create(@RequestBody Company company){
        return service.create(company);
    }


    @PatchMapping("/update")
    ResponseData<Company> update(@RequestBody Company company){
        return service.update(company);
    }

    @GetMapping("/get/{id}")
    ResponseData<Company> getOne(@PathVariable Long id){
        return service.findOne(id);
    }

    @GetMapping("/get")
    ResponseData<List<Company>> getAllCompany(){
        return service.getAll();
    }
    @DeleteMapping("/delete/{id}")
    ResponseData<Company> delete(@PathVariable Long id){
        return service.delete(id);
    }
}


