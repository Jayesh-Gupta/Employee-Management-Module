package com.example.service.serviceImpl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        super();
        this.employeeRepository=employeeRepository;
    }
    @Override
    public List<Employee> getAllEmployees(int deptId) {
        List<Employee> employees=new ArrayList<>();
        employeeRepository.findByDepartmentId(deptId).forEach(e->employees.add(e));
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        /*Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else {
            throw new ResourceNotFoundException("Employee","id",id);
        }*/

        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id)
        );
    }
    public Employee addEmployee(Employee employee){
        //System.out.println("hello emp "+employee);
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee,Integer id){
        Employee exisitingEmployee=employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

        exisitingEmployee.setFirstName(employee.getFirstName());
        exisitingEmployee.setLastName(employee.getLastName());
        exisitingEmployee.setLocation(employee.getLocation());

        employeeRepository.save(exisitingEmployee);
        return exisitingEmployee;
    }
    public void deleteEmployee(int id){
        employeeRepository.findById((Integer) id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

        employeeRepository.deleteById((Integer) id);
    }


   /* @Transactional
    public void saveEmployees(InputStream inputStream) throws IOException{
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream))){
            Iterable<CSVRecord> records= CSVFormat.DEFAULT.withHeader().parse(reader);

            ExecutorService executor = Executors.newFixedThreadPool(10);

            for(CSVRecord record:records){
                Runnable worker= new Runnable() {
                    @Override
                    public void run() {
                        Employee employee=new Employee();
                        employee.setFirstName(record.get("first_name"));
                        employee.setLastName(record.get("last_name"));
                        employee.setLocation(record.get("location"));


                        Department department=new Department();
                        department.setDepartmentName(record.get("departmentName"));
                       // System.out.println(department);
                        departmentService.addDepartment(department);

                        employee.setDepartment(department);
                        //addEmployee(employee);

                        try {
                            employeeRepository.saveAndFlush(employee);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                executor.execute(worker);

            }

            //executor.invokeAll();
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            System.out.println("Finished all threads");
        }
    }
*/


}