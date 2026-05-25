package com.example.expensetracker.expenceservice.entites;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.PropertyNamingStrategy;
import tools.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class expense {
    // amount
    //date
    // sender
    //

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(name = "user_id")
    private String userId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "merchant")
    private String merchant;

    @Column(name = "currency")
    private String currency;

    @Column(name = "category")
    private String category;

    @Column(name = "external_id")
    private String externalId;

    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    @PrePersist
    @PreUpdate
    private void generateExternalId() {
        if (this.externalId == null) {
            this.externalId = UUID.randomUUID().toString();
        }
        if (this.createdAt == null) {
            this.createdAt = new Timestamp(Instant.now().toEpochMilli());
        }
    }


}
