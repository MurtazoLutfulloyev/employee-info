package com.example.employeeinfo.component;

import com.example.employeeinfo.entity.Employee;
import com.example.employeeinfo.entity.Role;
import com.example.employeeinfo.entity.enums.Permission;
import com.example.employeeinfo.entity.enums.RoleType;
import com.example.employeeinfo.repository.EmployeeRepository;
import com.example.employeeinfo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner  {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    @Value("${spring.sql.init.mode}")
    private String modeType;

    @Override
    public void run(String... args) throws Exception {
     if (modeType.equals("always")){

         Permission[] values = Permission.values();

         Role admin = roleRepository.save(new Role("ADMIN", RoleType.ROlE_ADMIN, Arrays.asList(values)));

         Role employee = roleRepository.save(new Role("EMPLOYEE", RoleType.ROLE_EMPLOYEE,
                 Arrays.asList(Permission.ADD_EMPLOYEE, Permission.EDIT_EMPLOYEE,
                         Permission.DELETE_EMPLOYEE, Permission.VIEW_EMPLOYEE)));

         employeeRepository.save(new Employee("Admin",  "admin",
                 passwordEncoder.encode("admin123"), "murtazolutfulloyev@gmail.com",true, admin ));

         employeeRepository.save(new Employee("Employee", "employee",
                 passwordEncoder.encode("employee123"), "murtazolufulloyev@gmail.com",true, employee ));
     }
     }
}
