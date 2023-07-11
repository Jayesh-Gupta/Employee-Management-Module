package com.example.service;

import com.example.model.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Department getDepartmentById(Integer id);
    Department addDepartment(Department department);
    Department updateDepartment(Department department,Integer id);
    void deleteDepartment(int id);
}
