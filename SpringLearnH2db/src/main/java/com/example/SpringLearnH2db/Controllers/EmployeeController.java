package com.example.SpringLearnH2db.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.Dto.EmployeeDto;
import com.example.SpringLearnH2db.Entitys.EmployeeEntity;
import com.example.SpringLearnH2db.Services.EmployeesService;



@RestController
public class EmployeeController {


    private final EmployeesService employeesService;

    public EmployeeController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }


    @GetMapping("/getallemployee")
    public List<EmployeeDto> getEmployees() {
        return employeesService.getAllEmployee();
    }

    @GetMapping("/getemployee/{Id}")
    public EmployeeDto getEmployees(Long Id) {
        return employeesService.getEmployeeById(Id);
    }

    @PostMapping("/addemployee")
    public EmployeeDto CreateEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return  employeesService.CreateEmployee(employeeEntity);
        
    }
    
    

}
