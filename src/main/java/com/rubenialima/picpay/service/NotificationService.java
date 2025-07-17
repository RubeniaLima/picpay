package com.rubenialima.picpay.service;

import com.rubenialima.picpay.client.NotificationClient;
import com.rubenialima.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer){

        try {
            logger.info("sending notification...");

           var resp =  notificationClient.sendNotification(transfer);
           if(resp.getStatusCode().isError()){
               logger.error("Error while sending notification, status code is not OK");
           }
        }catch ( Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
