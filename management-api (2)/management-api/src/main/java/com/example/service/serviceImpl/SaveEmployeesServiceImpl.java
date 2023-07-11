package com.example.service.serviceImpl;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.service.DepartmentService;
import com.example.service.ProducerService;
import com.example.service.SaveEmployeesService;
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

@Service
public class SaveEmployeesServiceImpl implements SaveEmployeesService {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private DepartmentService departmentService;
    @Transactional
    public void saveEmployees(InputStream inputStream) throws IOException {
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream))){
            Iterable<CSVRecord> records= CSVFormat.DEFAULT.withHeader().parse(reader);


            List<Employee> employees=new ArrayList<>();

            for(CSVRecord record:records){

                Employee employee=new Employee();
                employee.setFirstName(record.get("first_name"));
                employee.setLastName(record.get("last_name"));
                employee.setLocation(record.get("location"));

                Department department=new Department();
                department.setDepartmentName(record.get("departmentName"));
                // System.out.println(department);

                departmentService.addDepartment(department);

                employee.setDepartment(department);
                employees.add(employee);
            }

            producerService.sendEmployeeData(employees);

        }
    }
}
