package com.hps.orchestraspa.controller;

import com.hps.orchestraspa.model.BookingRequest;
import com.hps.orchestraspa.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingRequestController {

    @Autowired
    private BookingRequestService bookingService;

    @PostMapping("/create")
    public ResponseEntity<BookingRequest> create(@RequestBody BookingRequest bookingRequest) throws Exception {
        return ResponseEntity.ok(bookingService.create(bookingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingRequest> get(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(bookingService.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<BookingRequest[]> getAll() throws Exception {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<BookingRequest> update(@RequestBody BookingRequest bookingRequest) throws Exception {
        return ResponseEntity.ok(bookingService.update(bookingRequest));
    }

    @PutMapping("/status")
    public ResponseEntity<BookingRequest> updateStatus(@RequestBody BookingRequest bookingRequest) throws Exception {
    // The Android app sends: { "bookingRequestId": 10, "status": "ACCEPTED" }
    return ResponseEntity.ok(bookingService.update(bookingRequest));
}
}