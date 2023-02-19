package com.example.employeeinfo.controller;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController{

    private final CurrencyService service;


    @GetMapping()
    public ResponseData<String> getCurrency(){
        return service.getCurrencyData();
    }




}
