package com.hps.orchestraspa.service;

import com.hps.orchestraspa.entities.NotificationData;
import com.hps.orchestraspa.model.Notification;
import com.hps.orchestraspa.repository.NotificationDataRepository;
import com.hps.orchestraspa.repository.BookingRequestDataRepository;
import com.hps.orchestraspa.repository.UserDataRepository;
import com.hps.orchestraspa.transform.TransformNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDataRepository notificationRepository;

    @Autowired
    private UserDataRepository userRepository;

    @Autowired
    private BookingRequestDataRepository bookingRepository;

    @Autowired
    private TransformNotificationService transformService;

    @Override
    public Notification create(Notification notification) throws Exception {
    // 1. Convert simple model (with just IDs) to Data Entity
    NotificationData data = transformService.transform(notification);

    // 2. THE FIX: Link the actual database objects
    // This tells Hibernate: "This notification belongs to User X and Booking Y"
    if (notification.getUserId() != null) {
        data.setUser(userRepository.findById(notification.getUserId())
            .orElseThrow(() -> new Exception("User ID " + notification.getUserId() + " not found")));
    }

    if (notification.getBookingRequestId() != null) {
        data.setBookingRequest(bookingRepository.findById(notification.getBookingRequestId())
            .orElseThrow(() -> new Exception("Booking ID " + notification.getBookingRequestId() + " not found")));
    }

    // 3. Save the now-populated data
    data = notificationRepository.save(data);

    // 4. Transform back to the model for the response
    return transformService.transform(data);
}
    
    @Override
    public Notification[] getAll() throws Exception {
        List<Notification> list = new ArrayList<>();
        notificationRepository.findAll().forEach(data -> {
            list.add(transformService.transform(data));
        });
        return list.toArray(new Notification[0]);
    }

    @Override
    public Notification get(Integer id) throws Exception {
        return notificationRepository.findById(id)
                .map(transformService::transform)
                .orElseThrow(() -> new Exception("Notification not found"));
    }

    @Override
    public void delete(Integer id) throws Exception {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification update(Notification notification) throws Exception {
        return null; 
    }
}