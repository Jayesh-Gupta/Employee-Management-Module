package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public List<Employee> findByDepartmentId(int id);
}
