package com.example.controller;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.service.SaveEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    private SaveEmployeesService saveEmployeesService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
    @GetMapping("/departments/{deptId}/employees")
    public List<Employee> getAllEmployee(@PathVariable("deptId")  int deptId){
        return employeeService.getAllEmployees(deptId);
    }
    @GetMapping("/departments/{deptId}/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById((Integer) id);
    }
    @PostMapping("/departments/{deptId}/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee,@PathVariable int deptId) {
        employee.setDepartment(new Department(deptId,""));
        return new ResponseEntity<Employee>(employeeService.addEmployee(employee),HttpStatus.CREATED);
    }
    @PutMapping("/departments/{deptId}/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id){
        return new ResponseEntity<Employee>(
                employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    @DeleteMapping("/departments/{deptId}/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        //System.out.println("Hii "+file);
        try{
            saveEmployeesService.saveEmployees(file.getInputStream());
            return ResponseEntity.ok("File Uploaded successfully");
        }
        catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

}