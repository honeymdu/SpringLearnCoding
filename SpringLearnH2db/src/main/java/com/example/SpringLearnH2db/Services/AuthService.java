package com.example.SpringLearnH2db.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Dto.JwtTokenDto;
import com.example.SpringLearnH2db.Dto.LoginDto;
import com.example.SpringLearnH2db.Entitys.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserService userService;

    private final SessionService sessionService;

    public JwtTokenDto Login(LoginDto loginDto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authentication);
        User user = (User) authenticated.getPrincipal();
        authenticationManager.authenticate(authentication);
        String Accesstoken = jwtService.GenerateAccessToken(user);
        String Refreshtoken = jwtService.GenerateRefreshToken(user);
        sessionService.GenerateNewSession(user, Refreshtoken);
        return JwtTokenDto.builder().refreshToken(Refreshtoken).accessToken(Accesstoken).id(user.getId()).build();

    }

    public JwtTokenDto refreshToken(String refreshToken) {
        Long UserId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.ValidateSession(refreshToken);
        User user = userService.getUserFromId(UserId);
        String Accesstoken = jwtService.GenerateAccessToken(user);
        return JwtTokenDto.builder().accessToken(Accesstoken).refreshToken(refreshToken)
                .id(user.getId()).build();

    }

}
