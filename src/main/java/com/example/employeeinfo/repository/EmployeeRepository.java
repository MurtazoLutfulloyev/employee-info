package com.example.employeeinfo.repository;

import com.example.employeeinfo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    Employee findByUsername(String username);

    boolean existsByUsername(String username);
}
