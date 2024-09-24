package com.example.SpringLearnH2db.Controllers;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.Dto.JwtTokenDto;
import com.example.SpringLearnH2db.Dto.LoginDto;
import com.example.SpringLearnH2db.Dto.SignUpDto;
import com.example.SpringLearnH2db.Dto.UserDto;
import com.example.SpringLearnH2db.Services.AuthService;
import com.example.SpringLearnH2db.Services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  private final AuthService authService;

  @PostMapping("/signUp")
  public ResponseEntity<UserDto> SignUp(@RequestBody SignUpDto signUpDto) {
    UserDto userDto = userService.signup(signUpDto);
    return ResponseEntity.ok(userDto);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtTokenDto> Login(@RequestBody LoginDto loginDto, HttpServletRequest request,
      HttpServletResponse response) {

    JwtTokenDto jwtTokenDto = authService.Login(loginDto);

    Cookie cookie = new Cookie("refreshToken", jwtTokenDto.getRefreshToken());
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
    return ResponseEntity.ok(jwtTokenDto);

  }

  // @PostMapping("/refreshv1")
  // public ResponseEntity<JwtTokenDto> refreshv1(HttpServletRequest request) {

  //   System.out.println(request.getCookies());
  //   String refreshToken = Arrays.stream(request.getCookies())

  //       .filter(cookie -> "refreshToken".equals(cookie.getName()))
  //       .findFirst()
  //       .map(Cookie::getValue)
  //       .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));
  //   JwtTokenDto jwtTokenDto = authService.refreshToken(refreshToken);
  //   return ResponseEntity.ok(jwtTokenDto);
  // }

  @PostMapping("/refresh")
  public ResponseEntity<?> refresh(HttpServletRequest request) {

    //System.out.println(request.getCookies().toString());
    String refreshToken = Arrays.stream(request.getCookies())

        .filter(cookie -> "refreshToken".equals(cookie.getName()))
        .findFirst()
        .map(Cookie::getValue)
        .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));
    JwtTokenDto jwtTokenDto = authService.refreshToken(refreshToken);
    return ResponseEntity.ok(jwtTokenDto);

  }

}
