package com.example.employeeinfo.service.impl;

import com.example.employeeinfo.dto.ResponseData;
import com.example.employeeinfo.entity.Company;
import com.example.employeeinfo.repository.CompanyRepository;
import com.example.employeeinfo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Override
    public ResponseData<Company> create(Company company) {
        ResponseData<Company> responseData = new ResponseData<>();
        try {
            Company saved = repository.save(company);
            responseData.setData(saved);
            responseData.setSuccess(true);
            responseData.setMessage("A new company has been created");
            return responseData;
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error in saving data !");
        }
        return responseData;
    }

    @Override
    public ResponseData<Company> update(Company company) {
        ResponseData<Company> responseData = new ResponseData<>();
        try {
            Optional<Company> byId = repository.findById(company.getId());
            if (byId.isPresent()) {
                Company saved = repository.save(company);
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
    public ResponseData<Company> delete(Long companyId) {
        ResponseData<Company> responseData = new ResponseData<>();
        try {
            repository.deleteById(companyId);
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
    public ResponseData<Company> findOne(Long id) {
        ResponseData<Company> responseData = new ResponseData<>();
        try {
            Optional<Company> company = repository.findById(id);
            if (company.isPresent()) {
                responseData.setSuccess(true);
                responseData.setData(company.get());
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMessage("Error when getting data with id : " + id);
        }
        return responseData;

    }

    @Override
    public ResponseData<List<Company>> getAll() {

        ResponseData<List<Company>> responseData = new ResponseData<>();

        try {
            List<Company> all = repository.findAll();
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
}
