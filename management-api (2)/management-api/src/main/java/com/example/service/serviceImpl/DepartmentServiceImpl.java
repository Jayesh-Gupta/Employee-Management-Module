package com.example.service.serviceImpl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        super();
        this.departmentRepository=departmentRepository;
    }
    @Override
//    @Cacheable(value = "dept")
    public List<Department> getAllDepartment() {
        //System.out.println("hello"+" "+departmentRepository.findAll().get(0).getDepartmentName());
        return departmentRepository.findAll();
    }
    @Override
    //@Cacheable(value="Department",key="#id")
    public Department getDepartmentById(Integer id) {
        /*Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else {
            throw new ResourceNotFoundException("Employee","id",id);
        }*/
        return departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id)
        );
    }
    @Override
    public Department addDepartment(Department department){
        //System.out.println("hello");
        return departmentRepository.save(department);
    }

    @Override
    //@CachePut(value="Department",key="#id")
    public Department updateDepartment(Department department,Integer id){
        Department exisitingDepartment=departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Department","id",id));

        exisitingDepartment.setDepartmentName(department.getDepartmentName());

        departmentRepository.save(exisitingDepartment);
        return exisitingDepartment;
    }
    @Override
   // @CacheEvict(value="Department",key="#id")
    public void deleteDepartment(int id){
        departmentRepository.findById((Integer) id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

        departmentRepository.deleteById((Integer) id);
    }
}
