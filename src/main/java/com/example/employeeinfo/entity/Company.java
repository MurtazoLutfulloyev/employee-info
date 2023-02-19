package com.example.employeeinfo.entity;

import com.example.employeeinfo.entity.template.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "company")
public class Company extends AbstractEntity {

    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "zip_code")
    private String zipCode;
}
