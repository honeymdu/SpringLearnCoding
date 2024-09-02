package com.example.SpringLearnH2db.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.SpringLearnH2db.Exceptions.ResourceNotFoundException;
import com.example.SpringLearnH2db.Repositories.UserRepoisitory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Service
public class UserService implements UserDetailsService{

    private final UserRepoisitory userRepoisitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepoisitory.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user not found with email "+username));
    }

}
