package com.example.SpringLearnH2db.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "EmployeeDetails")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;
    // private String email;
    // private Integer age;
    // private LocalDate dateOfBirth;
    // private Boolean isActive;
    // private String role;
    // private Double salary;

}
