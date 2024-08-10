package com.example.SpringLearnH2db.Services;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import com.example.SpringLearnH2db.Dto.EmployeeDto;
import com.example.SpringLearnH2db.Entitys.EmployeeEntity;
import com.example.SpringLearnH2db.Exceptions.ResourceNotFoundException;
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
                    .map(EmployeeEntity -> modelmapper.map(EmployeeEntity,EmployeeDto.class))
                    .collect(Collectors.toList());            
       
    }


    public Optional<EmployeeDto> getEmployeeById(Long id) {
       
        Optional<EmployeeEntity> EmployeeEntity = employeeRepository.findById(id);
       return EmployeeEntity.map( EmployeeEntity1-> modelmapper.map(EmployeeEntity1, EmployeeDto.class));

    }

    public EmployeeDto CreateEmployee(EmployeeDto EmployeeDto) {
       
       EmployeeEntity employeeEntity = modelmapper.map(EmployeeDto, EmployeeEntity.class);
       EmployeeEntity employeeSavedEntity = employeeRepository.save(employeeEntity);
        return modelmapper.map(employeeSavedEntity,EmployeeDto.getClass());

   }


    public EmployeeDto updateEmployeeById(Long id, EmployeeDto EmployeeDto) {
       
        IsEmployeeExists(id);
        EmployeeEntity employeeEntity = modelmapper.map(EmployeeDto, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity employeeSavedEntity = employeeRepository.save(employeeEntity);
        return modelmapper.map(employeeSavedEntity,EmployeeDto.getClass());

    }

    public boolean IsEmployeeExists(Long Id){
       Boolean IsEmployeeExists =employeeRepository.existsById(Id);
        if (!IsEmployeeExists) throw new ResourceNotFoundException("Resource Not Found");
        return true;
    }


    public Boolean deleteEmployeeById(Long id) {

        if (!IsEmployeeExists(id)) return false;
        employeeRepository.deleteById(id);
        return true;
    }


    public EmployeeDto partialupdateemployeeById(Long id, Map<String, Object> patchMapUpdate) {
        
        IsEmployeeExists(id);
        EmployeeEntity EmployeeEntity = employeeRepository.findById(id).get();
        patchMapUpdate.forEach((Field,Value)->{
           Field UpdatedFiled = ReflectionUtils.findRequiredField(EmployeeEntity.getClass(), Field);
            UpdatedFiled.setAccessible(true);
            ReflectionUtils.setField(UpdatedFiled, EmployeeEntity, Value);
        });
         return modelmapper.map(employeeRepository.save(EmployeeEntity), EmployeeDto.class);

        }
        
    }


