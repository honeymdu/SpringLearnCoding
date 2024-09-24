package com.example.SpringLearnH2db.Dto;

import java.util.Set;

import com.example.SpringLearnH2db.Entitys.Enums.Role;

import lombok.Data;

@Data
public class SignUpDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;

}
