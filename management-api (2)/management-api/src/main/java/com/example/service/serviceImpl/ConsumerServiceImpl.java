package com.example.service.serviceImpl;

import com.example.model.Employee;
import com.example.service.ConsumerService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private EmployeeService employeeService;
//    @KafkaListener(topics = "testTopic", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Received Message in group - group-id: " + message);
//    }

    @KafkaListener(topics = "testTopic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactoryEmployee")
    public void consumeEmployeeData(@Payload Employee employee) {
        //System.out.println("Received Message in group - group-id: " +  employee);
        //for(Employee employee:employees) {
            //System.out.println("Received Message in group - group-id: " + employee);
           employeeService.addEmployee(employee);
        //}
    }
}
