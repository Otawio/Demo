package com.sysmap.demo.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    @Autowired
    private KafkaTemplate<String, User> _kafka;
    @Value("${topic.name}")
    private String topic;
    public void send(User event) {
        _kafka.send(topic, event);
    }
    @KafkaListener(topics = "${topic.name}", groupId = "ms-demo")
    public void consume(ConsumerRecord<String, String> event) {
        System.out.println("NOSSO EVENTO -> "+event.value());
    }
}
