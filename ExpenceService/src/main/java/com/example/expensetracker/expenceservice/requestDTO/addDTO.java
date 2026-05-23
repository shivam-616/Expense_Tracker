package com.example.expensetracker.expenceservice.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record addDTO(
        // for manual entry from the user
       String userID,
        @NonNull String merchant,
        @NonNull String curreny,
        @JsonProperty("external_id") String externalId,
        @NonNull BigDecimal amount,
        @NonNull String category,
        @NonNull Timestamp date
        ) {}
