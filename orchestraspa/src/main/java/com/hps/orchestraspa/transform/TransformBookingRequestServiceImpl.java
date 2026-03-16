package com.hps.orchestraspa.transform;

import org.springframework.stereotype.Service;
import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

@Service
public class TransformBookingRequestServiceImpl implements TransformBookingRequestService {

    @Override
    public BookingRequestData transform(BookingRequest bookingRequest) {
        BookingRequestData bookingRequestData = new BookingRequestData();
        bookingRequestData.setBookingRequestId(bookingRequest.getBookingRequestId());
        bookingRequestData.setBookingDateTime(bookingRequest.getBookingDateTime());
        bookingRequestData.setStatus(bookingRequest.getStatus());
        
        // Note: The ServiceImpl handles fetching the actual UserData/MassageData 
        // objects from the DB, so we leave them null here for the Service to fill.
        return bookingRequestData;
    }

    @Override
    public BookingRequest transform(BookingRequestData data) {
        BookingRequest model = new BookingRequest();
        model.setBookingRequestId(data.getBookingRequestId());
        model.setBookingDateTime(data.getBookingDateTime());
        model.setStatus(data.getStatus());

        // THE FIX: Extract the IDs from the Entity objects and put them into the Model
        if (data.getUser() != null) {
            model.setUserId(data.getUser().getUserId());
        }
        
        if (data.getMassage() != null) {
            model.setMassageId(data.getMassage().getMassageId());
        }

        return model;
    }
}