package com.hps.orchestraspa.producer;

import com.hps.orchestraspa.model.BookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private static final Logger logger = LoggerFactory.getLogger(BookingProducer.class);
    
    private static final String TOPIC = "booking_topic";

    @Autowired
    private KafkaTemplate<String, BookingRequest> kafkaTemplate;

    public void sendBookingEvent(BookingRequest bookingRequest) {
        logger.info(String.format("#### -> Producing message -> %s", bookingRequest));

        this.kafkaTemplate.send(TOPIC, bookingRequest);
    }
}