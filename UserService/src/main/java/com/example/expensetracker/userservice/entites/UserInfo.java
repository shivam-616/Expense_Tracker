package com.example.expensetracker.userservice.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class UserInfo {

    // Removed the 'long id' field completely.
    // The userId coming from Kafka/Auth Service is now the true primary key.
    @Id
    @JsonProperty("user_id")
    private String userId;

    @NonNull
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String LastName;

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("phone_number")
    private long phoneNumber;

    @JsonProperty("profile_pic")
    private String profilPic;
}