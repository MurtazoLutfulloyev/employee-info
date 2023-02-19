package com.example.employeeinfo.entity;

import com.example.employeeinfo.entity.enums.Permission;
import com.example.employeeinfo.entity.template.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "employee")
public class Employee extends AbstractEntity implements UserDetails {

    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;
    @ManyToOne
    private Company company;


    @Column(name = "is_enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissionsList = this.role.getPermissionsList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Permission permission : permissionsList) {

            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Employee(String fullName, String username, String password, String email, Boolean enabled, Role role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
