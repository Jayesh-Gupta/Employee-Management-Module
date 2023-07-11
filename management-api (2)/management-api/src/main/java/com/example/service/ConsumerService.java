package com.example.service;

import com.example.model.Employee;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface ConsumerService {
    //void consume(String message);
    void consumeEmployeeData(Employee employee);
}
