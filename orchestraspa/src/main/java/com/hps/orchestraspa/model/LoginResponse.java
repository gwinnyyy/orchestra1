package com.hps.orchestraspa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer userId;
    private String userName;
    private String userRole; // "ADMIN" or "CUSTOMER"
    private String message;
    private boolean success;
}