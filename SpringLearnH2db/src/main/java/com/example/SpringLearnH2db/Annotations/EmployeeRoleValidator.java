package com.example.SpringLearnH2db.Annotations;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeRoleValidator implements ConstraintValidator <EmployeeRoleValidation , String>  {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        List<String> roles = List.of("ADMIN","USER");
        return roles.contains(role);
    }

}
