package com.hps.orchestraspa.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "massage")
public class MassageData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer massageId;
    private String massageName;
    private String massageDesc;

    @OneToMany(mappedBy="massage", cascade = CascadeType.ALL)
    private List<BookingRequestData> bookings;
}
