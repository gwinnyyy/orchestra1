package com.hps.orchestraspa.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notification")
public class NotificationData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;
    private String notificationDesc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToOne
    @JoinColumn(name = "booking_request")
    private BookingRequestData bookingRequest;

}
