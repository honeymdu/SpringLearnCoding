package com.example.SpringLearnH2db.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.Entitys.DepartmentEntity;
import com.example.SpringLearnH2db.Services.DepartmentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    public final DepartmentService departmentService;


    @GetMapping("/getdepartment/{id}")
    public ResponseEntity<DepartmentEntity> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentByid(id));        
        
    }

    @PostMapping("/createDepartment")
    public ResponseEntity<DepartmentEntity> CreateDepartment(@RequestBody DepartmentEntity departmentEntity) {
        DepartmentEntity UpdateddepartmentEntity = departmentService.CreateDepartment(departmentEntity);
            return new ResponseEntity<>(UpdateddepartmentEntity,HttpStatus.CREATED);
        
    }

    @PutMapping("/{departmentId}/manager/{employeeId}")
    public ResponseEntity<DepartmentEntity> AssignManagerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){
       return ResponseEntity.ok(departmentService.AssignManagerToDepartment(departmentId,employeeId));
    }



    

}
