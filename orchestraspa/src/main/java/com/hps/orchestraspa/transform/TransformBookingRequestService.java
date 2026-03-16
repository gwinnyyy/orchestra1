package com.hps.orchestraspa.transform;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;
public interface TransformBookingRequestService {
    BookingRequestData transform(BookingRequest bookingRequest);
    BookingRequest transform(BookingRequestData bookingRequestData);
}
