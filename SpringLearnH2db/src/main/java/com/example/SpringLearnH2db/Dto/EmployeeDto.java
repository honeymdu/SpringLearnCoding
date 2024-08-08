package com.example.SpringLearnH2db.Dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDto {

    private Long Id;
    private String Name;
    private String Email;
    private Integer Age;
    private LocalDate DateOfBirth;
    private Boolean IsActive;

}
