package com.example.controller;

import com.example.model.Department;
import com.example.service.DepartmentService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    //private final Logger LOG ;
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(DepartmentController.class.getName());

    public DepartmentController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
        //this.LOG=LoggerFactory.getLogger(getClass());
        //this.LOG=Logger.getLogger(LogExample.class.getName());
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartment(){
        //System.out.println("hii"+" "+departmentService.getAllDepartment().get(0).getDepartmentName());
        //System.out.println("hii");
        return departmentService.getAllDepartment();
    }
    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable int id){
        log.info("Getting user with ID {}."+id);
        return departmentService.getDepartmentById((Integer) id);
    }
    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return new ResponseEntity<Department>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department,@PathVariable("id") int id){
        return new ResponseEntity<Department>(
                departmentService.updateDepartment(department,id),HttpStatus.OK);
    }
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<String>("Department deleted successfully",HttpStatus.OK);
    }
}
