package com.example.employeeinfo.entity;

import com.example.employeeinfo.entity.enums.Permission;
import com.example.employeeinfo.entity.enums.RoleType;
import com.example.employeeinfo.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
public class Role extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;


    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Permission> permissionsList;
}
