package com.fullstackapi.employeemanager.service;


import com.fullstackapi.employeemanager.exception.UserNotFoundException;
import com.fullstackapi.employeemanager.model.Employee;
import com.fullstackapi.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {this.employeeRepo = employeeRepo;}

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        try {
            employeeRepo.deleteEmployeeById(id);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("Given id: " + id + " is not corresponding to a registered employee."));
    }

}
