//To Check whether the Employee age is Prime or not

package com.example.SpringLearnH2db.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EmployeeAgeValidator.class })
public @interface EmployeeAgeValidation {

    
    String message() default "Employee Age Should be a Prime Number";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
