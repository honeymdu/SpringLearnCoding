package com.example.SpringLearnH2db.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Entitys.DepartmentEntity;
import com.example.SpringLearnH2db.Entitys.EmployeeEntity;
import com.example.SpringLearnH2db.Repositories.DepartmentRepository;
import com.example.SpringLearnH2db.Repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentEntity getDepartmentByid(Long id) {

        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity CreateDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity AssignManagerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department -> employeeEntity.map(employee -> {
            department.setManager(employee);
            return departmentRepository.save(department);
        })).orElse(null);

    }
}
