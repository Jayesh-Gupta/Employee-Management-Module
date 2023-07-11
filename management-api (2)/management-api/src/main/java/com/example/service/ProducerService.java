package com.example.service;

import com.example.model.Employee;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface ProducerService {
    //void sendMessage(String message);
    void sendEmployeeData(List<Employee> employees);
}
