package com.example.SpringLearnH2db.Dto;

import java.time.LocalDate;

public class EmployeeDto {

    private Long Id;
    private String Name;
    private String Email;
    private Integer Age;
    private LocalDate DateOfBirth;
    private Boolean IsActive;

    public EmployeeDto() {
    }
    
    public EmployeeDto(Long id, String name, String email, Integer age, LocalDate dateOfBirth, Boolean isActive) {
        Id = id;
        Name = name;
        Email = email;
        Age = age;
        DateOfBirth = dateOfBirth;
        IsActive = isActive;
    }
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public Integer getAge() {
        return Age;
    }
    public void setAge(Integer age) {
        Age = age;
    }
    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
    public Boolean getIsActive() {
        return IsActive;
    }
    public void setIsActive(Boolean isActive) {
        IsActive = isActive;
    }

}
