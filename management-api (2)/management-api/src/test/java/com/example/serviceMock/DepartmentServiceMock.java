package com.example.serviceMock;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import com.example.service.serviceImpl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class DepartmentServiceMock {

    @Mock
    private DepartmentRepository departmentRepository;
    private DepartmentServiceImpl mockService;

    @BeforeEach
    public void setup() {
        this.mockService=new DepartmentServiceImpl(this.departmentRepository) ;
    }

    public List<Department> getAllDepartments(){

        mockService.getAllDepartment();
        return Mockito.verify(departmentRepository).findAll();
    }
}
