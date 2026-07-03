package lk.jiat.techmart.service.impl;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import lk.jiat.techmart.service.NotificationService;

import java.util.logging.Logger;

@Stateless
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER =
            Logger.getLogger(NotificationServiceImpl.class.getName());

    @Override
    @Asynchronous
    public void sendOrderNotification(Long orderId) {

        LOGGER.info("=======================================");
        System.out.println("ASYNC STARTED");
        LOGGER.info("Asynchronous Notification Started");
        System.out.println("ASYNC FINISHED");
        LOGGER.info("Processing Order ID : " + orderId);

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }

        LOGGER.info("Notification Completed for Order : " + orderId);
        LOGGER.info("=======================================");

    }
}