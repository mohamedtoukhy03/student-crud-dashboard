package com.School.StudentManagementSystem.security;

import com.School.StudentManagementSystem.entity.Admin;
import com.School.StudentManagementSystem.repository.AdminRepository;
import com.School.StudentManagementSystem.service.AdminUserDetails;

import lombok.Data;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Data
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(AdminRepository adminRepository) {
        return (email) -> {
            Optional<Admin> admin = adminRepository.findByEmail(email);
            AdminUserDetails adminDetailService = new AdminUserDetails(admin);
            if (admin.isEmpty()) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return adminDetailService;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthProvider) throws Exception {
        http
                .authenticationProvider(daoAuthProvider)           
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/deleteStudent", "/updateForm").hasRole("MANAGER")
                        .requestMatchers("/css/**", "/javaScript/**", "/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/authenticateUser")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm?logout")
                .permitAll()
        );

        return http.build();
    }
}

