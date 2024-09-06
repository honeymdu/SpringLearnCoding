package com.example.SpringLearnH2db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest()
                        .authenticated())
                //.formLogin(Customizer.withDefaults())  // enable default login form
                .csrf(csrfconfig -> csrfconfig.disable()) //to disable csrf token auth
              //  .sessionManagement(sessionconfig -> sessionconfig
                 //       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //to remove session mangement
                .build();

    }

//     @Bean
//     UserDetailsService InMemoryUserDetailService() {   //in memory Userdetails service for development only

//         UserDetails normalUser = User.withUsername("Himanshu")
//                 .password(new BCryptPasswordEncoder()
//                         .encode("password"))
//                 .roles("USER").build();

//         UserDetails adminUser = User.withUsername("admin")
//                 .password(new BCryptPasswordEncoder()
//                         .encode("admin"))
//                 .roles("ADMIN").build();

//         return new InMemoryUserDetailsManager(normalUser, adminUser);
//     }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

}
