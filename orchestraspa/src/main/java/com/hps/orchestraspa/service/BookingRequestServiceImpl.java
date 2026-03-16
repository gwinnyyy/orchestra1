package com.hps.orchestraspa.service;

import com.hps.orchestraspa.entities.BookingRequestData;
import com.hps.orchestraspa.enums.Status;
import com.hps.orchestraspa.model.BookingRequest;
import com.hps.orchestraspa.repository.BookingRequestDataRepository;
import com.hps.orchestraspa.repository.MassageDataRepository;
import com.hps.orchestraspa.repository.UserDataRepository;
import com.hps.orchestraspa.transform.TransformBookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hps.orchestraspa.producer.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

    @Autowired
    private BookingRequestDataRepository bookingRepo;

    @Autowired
    private UserDataRepository userRepo;

    @Autowired
    private MassageDataRepository massageRepo;

    @Autowired
    private TransformBookingRequestService transformService;


    @Autowired
    private BookingProducer bookingProducer; 

    @Override
    public BookingRequest create(BookingRequest bookingRequest) throws Exception {
        // 1. Transform basic fields using the renamed parameter
        BookingRequestData data = transformService.transform(bookingRequest);
        
        // 2. Set Default Status if not provided by Android
        if (data.getStatus() == null) {
            data.setStatus(Status.PENDING);
        }

        // 3. LINK RELATIONSHIPS (Fetch from DB using IDs from bookingRequest)
        data.setUser(userRepo.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new Exception("User not found")));
        
        data.setMassage(massageRepo.findById(bookingRequest.getMassageId())
                .orElseThrow(() -> new Exception("Massage not found")));

        // 4. Save to Database
        data = bookingRepo.save(data);

        // 5. KAFKA: Trigger the notification event
        BookingRequest savedModel = transformService.transform(data);
        savedModel.setUserId(data.getUser().getUserId());
        savedModel.setMassageId(data.getMassage().getMassageId());
        bookingProducer.sendBookingEvent(savedModel);
        // bookingProducer.sendBookingEvent(data); 

        return transformService.transform(data);
    }

    @Override
    public BookingRequest update(BookingRequest bookingRequest) throws Exception {
        // 1. Fetch the existing booking from the DB
        BookingRequestData existing = bookingRepo.findById(bookingRequest.getBookingRequestId())
                .orElseThrow(() -> new Exception("Booking not found"));
        
        // 2. Update the status (ACCEPTED, REJECTED, or COMPLETED)
        existing.setStatus(bookingRequest.getStatus());
        
        // 3. Save the change
        BookingRequestData updatedData = bookingRepo.save(existing);
        BookingRequest resultModel = transformService.transform(updatedData);
        
        // 4. THE KAFKA SHOUT: Notify the customer about the status change
        // You can use the same producer or a new method
        bookingProducer.sendBookingEvent(resultModel); 
        
        return resultModel;
    }

    @Override
    public BookingRequest[] getAll() throws Exception {
        List<BookingRequest> list = new ArrayList<>();
        bookingRepo.findAll().forEach(data -> list.add(transformService.transform(data)));
        return list.toArray(new BookingRequest[0]);
    }

    @Override
    public BookingRequest get(Integer bookingRequestId) throws Exception {
        return bookingRepo.findById(bookingRequestId)
                .map(transformService::transform)
                .orElseThrow(() -> new Exception("Booking ID " + bookingRequestId + " not found"));
    }

    @Override
    public void delete(Integer bookingRequestId) throws Exception {
        if (!bookingRepo.existsById(bookingRequestId)) {
            throw new Exception("Cannot delete. Booking ID " + bookingRequestId + " not found");
        }
        bookingRepo.deleteById(bookingRequestId);
    }


}