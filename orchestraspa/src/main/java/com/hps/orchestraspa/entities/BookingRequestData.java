package com.hps.orchestraspa.entities;

import com.hps.orchestraspa.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "booking_request")
public class BookingRequestData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingRequestId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToOne
    @JoinColumn(name = "massage_id")
    private MassageData massage;

    private LocalDateTime bookingDateTime;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy="bookingRequest", cascade = CascadeType.ALL)
    private List<NotificationData> notifications;
    
}
