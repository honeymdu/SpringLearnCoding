package com.example.SpringLearnH2db.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator <EmployeeAgeValidation , Integer>{

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        
        if(age==1)
        return false;

        if(age<=3)
        return true;

        if(age%2==0||age%3==0)
        return false;

        for(int i =2; i<=Math.sqrt(age);i++){
            if(age%i==0){
                return false;
            }
        }
        return true;
    
    }

}
