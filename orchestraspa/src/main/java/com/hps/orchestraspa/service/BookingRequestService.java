package com.hps.orchestraspa.service;
import com.hps.orchestraspa.model.*;
public interface BookingRequestService {
    BookingRequest[] getAll() throws Exception;
    BookingRequest get(Integer bookingRequestId) throws Exception;
    BookingRequest create(BookingRequest bookingRequest) throws Exception;
    BookingRequest update(BookingRequest bookingRequest) throws Exception;
    void delete(Integer bookingRequestId) throws Exception;
}
