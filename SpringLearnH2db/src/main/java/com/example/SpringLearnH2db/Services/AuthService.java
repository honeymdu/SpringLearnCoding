package com.example.SpringLearnH2db.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Dto.LoginDto;
import com.example.SpringLearnH2db.Entitys.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public String Login(LoginDto loginDto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authentication);
        User user = (User) authenticated.getPrincipal();
        authenticationManager.authenticate(authentication);
        String token = jwtService.GenerateToken(user);
        return token;

    }

}
