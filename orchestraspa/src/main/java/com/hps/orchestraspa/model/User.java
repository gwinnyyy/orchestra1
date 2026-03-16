package com.hps.orchestraspa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Required for Kafka/JSON deserialization
@AllArgsConstructor
public class User {
    Integer userId;
    String userName;
    String userEmail;
    String userPassword;
    String userRole;
}
