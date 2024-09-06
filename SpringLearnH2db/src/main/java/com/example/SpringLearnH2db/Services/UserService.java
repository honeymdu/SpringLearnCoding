package com.example.SpringLearnH2db.Services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Dto.SignUpDto;
import com.example.SpringLearnH2db.Dto.UserDto;
import com.example.SpringLearnH2db.Entitys.User;
import com.example.SpringLearnH2db.Exceptions.ResourceNotFoundException;
import com.example.SpringLearnH2db.Repositories.UserRepoisitory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final UserRepoisitory userRepoisitory;

  private final ModelMapper modelMapper;

  private final PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepoisitory.findByEmail(username)
        .orElseThrow(() -> new ResourceNotFoundException("user not found with email " + username));
  }

  public UserDto signup(SignUpDto signUpDto) {
    Optional<User> user = userRepoisitory.findByEmail(signUpDto.getEmail());
    if (user.isPresent()) {
      throw new BadCredentialsException("User already Present with email " + signUpDto.getEmail());
    }

    User tobeCreateuser = modelMapper.map(signUpDto, User.class);
    tobeCreateuser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    return modelMapper.map(userRepoisitory.save(tobeCreateuser), UserDto.class);

  }

}
