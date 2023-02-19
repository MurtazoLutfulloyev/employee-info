package com.example.employeeinfo.service.impl;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Employee;
import com.example.employeeinfo.repository.EmployeeRepository;
import com.example.employeeinfo.service.EmailSenderService;
import com.example.employeeinfo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    private final EmailSenderService emailSenderService;


    @Override
    public ResponseData<Employee> create(Employee employee) {
        ResponseData<Employee> responseData = new ResponseData<>();
        try {
            String email = employee.getEmail();
            Employee saved = repository.save(employee);
            responseData.setData(saved);
            responseData.setSuccess(true);
            responseData.setMessage("A new employee has been created, our send verification message to your email");
            Employee byEmail = repository.findByEmail(email);
            Long id = byEmail.getId();
            emailSenderService.sendSimpleEmail(email, "Activation link","You can activate your profile via this link\n http://localhost:8085/api/email/"+id);
            return responseData;
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error in saving data !");
        }
        return responseData;
    }

    @Override
    public ResponseData<Employee> update(Employee employee) {
        ResponseData<Employee> responseData = new ResponseData<>();
        try {
            Optional<Employee> byId = repository.findById(employee.getId());
            if (byId.isPresent()) {
                Employee saved = repository.save(employee);
                responseData.setData(saved);
                responseData.setSuccess(true);
                responseData.setMessage("Successfully updated");
                return responseData;

            }

        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error while updating data!");
        }
        return responseData;
    }

    @Override
    public ResponseData<Employee> delete(Long employeeId) {
        ResponseData<Employee> responseData = new ResponseData<>();
        try {
            repository.deleteById(employeeId);
            responseData.setMessage("Deleted data");
            responseData.setSuccess(true);
            return responseData;
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error when deleting data");
        }
        return responseData;
    }

    @Override
    public ResponseData<Employee> findOne(Long id) {
        ResponseData<Employee> responseData = new ResponseData<>();
        try {
            Optional<Employee> employee = repository.findById(id);
            if (employee.isPresent()) {
                responseData.setSuccess(true);
                responseData.setData(employee.get());
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error when getting data with id : " + id);
        }
        return responseData;
    }

    @Override
    public ResponseData<Employee> activationEmployee(Long id) {

        ResponseData<Employee> responseData = new ResponseData<>();
        try {
            Optional<Employee> employee = repository.findById(id);
            if (employee.isPresent()) {
                Employee employee1 = employee.get();
                employee1.setEnabled(true);
                repository.save(employee1);
                responseData.setSuccess(true);
                responseData.setData(employee.get());
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error when getting data with id : " + id);
        }
        return responseData;
    }

    @Override
    public ResponseData<List<Employee>> getAll() {

        ResponseData<List<Employee>> responseData = new ResponseData<>();

        try {
            List<Employee> all = repository.findAll();
            if (!all.isEmpty()) {
                responseData.setSuccess(true);
                responseData.setData(all);
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error getting data");
        }
        return responseData;

    }

    @Override
    public Employee findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
