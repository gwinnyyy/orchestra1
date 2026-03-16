package com.hps.orchestraspa.service;

import com.hps.orchestraspa.model.Notification;

public interface NotificationService {
    Notification[] getAll() throws Exception;
    Notification get(Integer massageId) throws Exception;
    Notification create(Notification massage) throws Exception;
    Notification update(Notification massage) throws Exception;
    void delete(Integer massageId) throws Exception;
}
