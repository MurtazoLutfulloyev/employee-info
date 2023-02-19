package com.example.employeeinfo.service;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    ResponseData<Employee> create(Employee employee);
    ResponseData<Employee> update(Employee employee);
    ResponseData<Employee> delete(Long employeeId);

    ResponseData<Employee> findOne(Long id);
     Employee findByUsername(String username);

    ResponseData<Employee> activationEmployee(Long id);

    ResponseData<List<Employee>> getAll();


}
