package ru.job4j.service;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class MessagesService {

    @KafkaListener(topics = "massage")
    public void messageOutput(String massage) {
        System.out.println(massage);
    }
}
