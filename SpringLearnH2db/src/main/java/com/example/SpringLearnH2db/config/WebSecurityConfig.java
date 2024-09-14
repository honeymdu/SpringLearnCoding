package com.example.SpringLearnH2db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.SpringLearnH2db.Filters.JwtAuthFilter;
import com.example.SpringLearnH2db.Handlers.Oauth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final Oauth2SuccessHandler oauth2SuccessHandler;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/posts/getAllPost").hasRole("USER")
                        // .requestMatchers("/employee/addemployee").hasRole("ADMIN")
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest()
                        .authenticated())
                // .formLogin(Customizer.withDefaults()) // enable default login form
                .csrf(csrfconfig -> csrfconfig.disable()) // to disable csrf token auth
                // .sessionManagement(sessionconfig -> sessionconfig
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //to remove session
                // mangement
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauthconfig -> oauthconfig
                        .failureUrl("/login?error=true")
                        .successHandler(oauth2SuccessHandler))
                .build();

    }

    // @Bean
    // UserDetailsService InMemoryUserDetailService() { //in memory Userdetails
    // service for development only

    // UserDetails normalUser = User.withUsername("Himanshu")
    // .password(new BCryptPasswordEncoder()
    // .encode("password"))
    // .roles("USER").build();

    // UserDetails adminUser = User.withUsername("admin")
    // .password(new BCryptPasswordEncoder()
    // .encode("admin"))
    // .roles("ADMIN").build();

    // return new InMemoryUserDetailsManager(normalUser, adminUser);
    // }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
