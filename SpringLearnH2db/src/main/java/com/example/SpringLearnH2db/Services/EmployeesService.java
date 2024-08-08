package com.example.SpringLearnH2db.Services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Dto.EmployeeDto;
import com.example.SpringLearnH2db.Entitys.EmployeeEntity;
import com.example.SpringLearnH2db.Repositories.EmployeeRepository;


@Service
public class EmployeesService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelmapper;

    

    
    public EmployeesService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelmapper = modelMapper;
    }


    public List<EmployeeDto> getAllEmployee() {

       List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeEntities
                    .stream()
                    .map(EmployeeEntity -> modelmapper.map(employeeEntities,EmployeeDto.class))
                    .collect(Collectors.toList());
            
       
    }


    public EmployeeDto getEmployeeById(Long id) {
       
         Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
         return modelmapper.map(employeeEntity, EmployeeDto.class);

    }

    public EmployeeDto CreateEmployee(EmployeeEntity employeeEntity) {
       
           employeeEntity = employeeRepository.save(employeeEntity);
           return modelmapper.map(employeeEntity, EmployeeDto.class);
        

   }

  
}
