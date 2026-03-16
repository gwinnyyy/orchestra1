package com.hps.orchestraspa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Required for Kafka/JSON deserialization
@AllArgsConstructor
public class Notification {
    Integer notificationId;
    String notificationDesc;
    Integer userId;
    Integer bookingRequestId;
    
    
}
