package com.example.controller;

import com.example.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final ProducerService producerService;


    private final KafkaTemplate<String, String> kafkaTemplate1;

    public KafkaController(ProducerService producerService, KafkaTemplate<String, String> kafkaTemplate1) {
        this.producerService = producerService;
        this.kafkaTemplate1 = kafkaTemplate1;
    }

   @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody String message) {
        kafkaTemplate1.send("mytopic",message);
   }
}
