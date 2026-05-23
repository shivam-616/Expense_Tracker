package com.example.expensetracker.expenceservice.producer;

import com.example.expensetracker.expenceservice.requestDTO.addDTO;
import com.example.expensetracker.expenceservice.service.expenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseConsumer {
    private expenseService expenseService;

    @Autowired
    ExpenseConsumer(expenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(addDTO eventData) {
        try {
            // Todo: Make it transactional, and check if duplicate event (Handle idempotency)
            expenseService.saveExpense(eventData);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }
}
