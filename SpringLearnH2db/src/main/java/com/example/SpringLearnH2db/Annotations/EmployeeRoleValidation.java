package com.example.SpringLearnH2db.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EmployeeRoleValidator.class })
public @interface EmployeeRoleValidation {

    String message() default "Role for Employee Can be ADMIN or USER only";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
