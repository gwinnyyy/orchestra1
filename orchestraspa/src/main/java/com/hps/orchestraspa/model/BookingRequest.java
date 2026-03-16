package com.hps.orchestraspa.model;

import java.time.LocalDateTime;
import com.hps.orchestraspa.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Required for Kafka/JSON deserialization
@AllArgsConstructor
public class BookingRequest {
    Integer bookingRequestId;

    Integer userId;
    Integer massageId;
    LocalDateTime bookingDateTime;
    Status status;
}
