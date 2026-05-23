package com.example.sms.sms_extract.producer;

import com.example.sms.sms_extract.dto.TransactionDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class smsdataProducer {
    private final KafkaTemplate<String , TransactionDetailsDTO> kafkaTemplate;

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    @Autowired
    public smsdataProducer(KafkaTemplate<String , TransactionDetailsDTO >kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToKafka(String userId, TransactionDetailsDTO transactionDetailsDTO) {
        Message<TransactionDetailsDTO> message = MessageBuilder
                .withPayload(transactionDetailsDTO)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .setHeader(KafkaHeaders.KEY, userId) // Set userId as the Kafka Key
                .build();
        kafkaTemplate.send(message);
    }

}
