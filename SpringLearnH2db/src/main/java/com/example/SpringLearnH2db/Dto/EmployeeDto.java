package com.example.SpringLearnH2db.Dto;

import java.time.LocalDate;

//import com.example.SpringLearnH2db.Annotations.EmployeeAgeValidation;
import com.example.SpringLearnH2db.Annotations.EmployeeRoleValidation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Negative;
//import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
//import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.AssertFalse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDto {

    private Long id;
    @NotNull(message = "Required Field in Employee : name")
    @NotEmpty(message = "Name can not be Empty")
    @NotBlank(message = "Name Can not Blank")
    @Size(min = 3,max = 10,message = "Number or character should be in range {3,10}")
    private String name;

    @Email(message ="Please enter a valid Email")
    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotNull
    @Max(value = 45,message = "Age of Employee Can not be more then 45")
    @Min(value = 18,message = "Age of Employee Can not be more then 18")
    //@EmployeeAgeValidation
    private Integer age;

    @PastOrPresent(message = "date of birth should not be in the Future")
    private LocalDate dateOfBirth;

    
    @AssertTrue(message = "isActive Value Should be True")
    //@AssertFalse(message = "isActive Value Should be False")
    private Boolean isActive;

    @NotBlank
    //@Pattern(regexp = "^(ADMIN|USER)$",message = "Role for Employee Can be ADMIN or USER only")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary of Employee can not be Null")
    @Positive(message = "Salary of Employee should be positive or greater then Zero")
    @Digits(integer=6,fraction=2,message = "Salery should be of (XXXX.YY)")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value= "100.50")
    //@PositiveOrZero(message = "Salary of Employee should be positive or Zero")
   // @Negative(message = "Salary of Employee should be negative or less then Zero")
    //@NegativeOrZero(message = "Salary of Employee should be negative or Zero")
    private Double salary;

}
