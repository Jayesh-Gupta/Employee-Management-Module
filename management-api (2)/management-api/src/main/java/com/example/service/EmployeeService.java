package com.example.service;

import com.example.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface EmployeeService {

    List<Employee> getAllEmployees(int deptId);
    Employee getEmployeeById(Integer id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee,Integer id);
    void deleteEmployee(int id);
}
