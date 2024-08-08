package com.example.SpringLearnH2db.Entitys;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "EmployeeDetails")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Name;
    private String Email;
    private Integer Age;
    private LocalDate DateOfBirth;
    private Boolean IsActive;

}
