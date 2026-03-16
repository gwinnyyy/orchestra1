package com.hps.orchestraspa.transform;

import org.springframework.stereotype.Service;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

@Service
public class TransformNotificationServiceImpl implements TransformNotificationService {
        @Override
    public NotificationData transform(Notification notification){
        NotificationData notificationData = new  NotificationData();
        notificationData.setNotificationId(notification.getNotificationId());
        notificationData.setNotificationDesc(notification.getNotificationDesc());
   
        return notificationData;
    }

    @Override
    public Notification transform(NotificationData data) {
        Notification model = new Notification();
        model.setNotificationId(data.getNotificationId());
        model.setNotificationDesc(data.getNotificationDesc());

        // Pull the IDs from the Entity objects
        if (data.getUser() != null) {
            model.setUserId(data.getUser().getUserId());
        }
        
        if (data.getBookingRequest() != null) {
            model.setBookingRequestId(data.getBookingRequest().getBookingRequestId());
        }

        return model;
    }
}
