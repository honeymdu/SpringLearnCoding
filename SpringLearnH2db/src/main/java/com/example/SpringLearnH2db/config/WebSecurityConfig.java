package com.example.SpringLearnH2db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/posts/getAllPost").hasRole("USER")
                        .requestMatchers("/employee/addemployee").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())  // enable default login form
                .csrf(csrfconfig -> csrfconfig.disable()) //to disable csrf token auth
                .sessionManagement(sessionconfig -> sessionconfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //to remove session mangement
                .build();

    }

    @Bean
    UserDetailsService InMemoryUserDetailService() {   //in memory Userdetails service for development only

        UserDetails normalUser = User.withUsername("Himanshu")
                .password(new BCryptPasswordEncoder()
                        .encode("password"))
                .roles("USER").build();

        UserDetails adminUser = User.withUsername("admin")
                .password(new BCryptPasswordEncoder()
                        .encode("admin"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
