package com.example.auth_service.eventProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoProducer {

    // 1. Changed UserInfoDto to UserInfoEvent to match the data you are actually sending
    private final KafkaTemplate<String, UserInfoEvent> kafkaTemplate;

    // 2. Fixed the property key to perfectly match application.properties
    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    @Autowired
    public UserInfoProducer(KafkaTemplate<String, UserInfoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventTokafka(UserInfoEvent userInfoEvent){
        Message<UserInfoEvent> message = MessageBuilder
                .withPayload(userInfoEvent)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();
        kafkaTemplate.send(message);
    }
}