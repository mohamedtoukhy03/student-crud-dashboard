package com.School.StudentManagementSystem.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.School.StudentManagementSystem.entity.Admin;

import lombok.Data;

@Service
@Data
public class AdminUserDetails implements  UserDetails{
    private final Optional<Admin> admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return admin.get().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.get().getPassword();

    }

    @Override
    public String getUsername() {
        return admin.get().getEmail();
    }

    

}
