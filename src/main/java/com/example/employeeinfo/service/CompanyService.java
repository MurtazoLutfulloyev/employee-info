package com.example.employeeinfo.service;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    ResponseData<Company> create(Company company);
    ResponseData<Company> update(Company company);
    ResponseData<Company> delete(Long company);

    ResponseData<Company> findOne(Long id);

    ResponseData<List<Company>> getAll();
}
