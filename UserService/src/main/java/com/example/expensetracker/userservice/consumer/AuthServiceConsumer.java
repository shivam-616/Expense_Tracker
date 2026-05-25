package com.example.expensetracker.userservice.consumer;


import com.example.expensetracker.userservice.entites.UserInfo;
import com.example.expensetracker.userservice.entites.userinfoDto;
import com.example.expensetracker.userservice.serviec.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class   AuthServiceConsumer {
    @Autowired
    private UserService userservice;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(userinfoDto eventdata) {
        try {
            //
            System.out.println("listing to the evenet");
            userservice.createOrUpdateUser(eventdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
