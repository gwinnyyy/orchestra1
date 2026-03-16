package com.hps.orchestraspa.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List; 

@Data
@Entity
@Table(name = "user")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userEmail;
    private String userName;
    private String userPassword;
    private String userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookingRequestData> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NotificationData> notifications;
}
