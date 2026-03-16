package com.hps.orchestraspa.transform;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;
public interface TransformNotificationService {
    NotificationData transform(Notification notification);
    Notification transform(NotificationData notificationData);
}
