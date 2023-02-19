package com.example.employeeinfo.entity;

import com.example.employeeinfo.entity.enums.Permission;
import com.example.employeeinfo.entity.template.AbstractEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;


    private boolean accountNonBlocked=true;
    private boolean accountNonExpired=true;
    private boolean credentialNonExpired=true;
    private boolean enabled=true;

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
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String firstName, String lastName, String phoneNumber, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
