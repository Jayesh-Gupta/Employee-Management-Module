package com.example.service.serviceImpl;

import com.example.model.Employee;
import com.example.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String, Employee> kafkaTemplate2;
    private final String TOPIC = "testTopic";

    public ProducerServiceImpl( KafkaTemplate<String, Employee> kafkaTemplate2) {
        super();
        this.kafkaTemplate2 = kafkaTemplate2;
    }
    /*public void sendMessage(String message) {

        this.kafkaTemplate1.send(TOPIC, message);
    }*/

    @Override
    public void sendEmployeeData(List<Employee> employees) {
        for(Employee employee:employees){
            kafkaTemplate2.send("testTopic",employee);
        }
    }
}
