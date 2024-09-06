package com.example.SpringLearnH2db.Controllers;

import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<JwtTokenDto> Login(@RequestBody LoginDto loginDto) {

    String token = authService.Login(loginDto);
    return ResponseEntity.ok(JwtTokenDto.builder().token(token).build());

  }

}
