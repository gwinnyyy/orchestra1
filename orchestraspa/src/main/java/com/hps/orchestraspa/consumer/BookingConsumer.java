package com.hps.orchestraspa.consumer;

import com.hps.orchestraspa.model.BookingRequest;
import com.hps.orchestraspa.enums.Status;
import com.hps.orchestraspa.model.Notification;
import com.hps.orchestraspa.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BookingConsumer.class);

    @Autowired
    private NotificationService notificationService;

    // Only ONE listener per group!
    @KafkaListener(topics = "booking_topic", groupId = "orchestra-group")
    public void consume(BookingRequest bookingRequest) {
        logger.info(String.format("#### -> Consumed message -> %s", bookingRequest));

        try {
            // Rule 1: If it's a brand NEW booking (PENDING), notify the Admin
            if (bookingRequest.getStatus() == Status.PENDING) {
                Notification adminNotification = new Notification();
                adminNotification.setNotificationDesc("New Booking Request from User ID: " + bookingRequest.getUserId());
                adminNotification.setBookingRequestId(bookingRequest.getBookingRequestId());
                adminNotification.setUserId(1); // Assuming Admin is User 1

                notificationService.create(adminNotification);
                logger.info("Notification successfully saved for Admin.");
            }

            // Rule 2: If the status is ACCEPTED or REJECTED, notify the Customer
            if (bookingRequest.getStatus() != Status.PENDING) {
                Notification customerNote = new Notification();
                customerNote.setUserId(bookingRequest.getUserId()); // The customer's ID
                customerNote.setNotificationDesc("Your booking is now: " + bookingRequest.getStatus());
                customerNote.setBookingRequestId(bookingRequest.getBookingRequestId());
                
                notificationService.create(customerNote);
                logger.info("Notification successfully saved for Customer.");
            }

        } catch (Exception e) {
            logger.error("Error processing notification: " + e.getMessage());
        }
    }
}