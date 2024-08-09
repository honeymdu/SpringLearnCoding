package com.example.SpringLearnH2db.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator <EmployeeAgeValidation , Integer>{

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        if(age%2==0)
        return true;
        else 
        return false;
    }

}
