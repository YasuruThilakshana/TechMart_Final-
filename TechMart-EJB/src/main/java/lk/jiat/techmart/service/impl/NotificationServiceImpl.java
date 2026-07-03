package lk.jiat.techmart.service.impl;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import lk.jiat.techmart.dto.OrderMessageDTO;
import lk.jiat.techmart.service.NotificationService;

import java.util.logging.Logger;

@Stateless
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER =
            Logger.getLogger(NotificationServiceImpl.class.getName());

    @Override
    @Asynchronous
    public void sendOrderNotification(OrderMessageDTO order) {

        sendCustomerEmail(order);

        generateInvoice(order);

        updateAnalytics(order);

        notifyAdmin(order);

    }

    @Override
    public void sendCustomerEmail(OrderMessageDTO order) {

        LOGGER.info("Customer Email Sent : "
                + order.getCustomerName());

    }

    @Override
    public void generateInvoice(OrderMessageDTO order) {

        LOGGER.info("Invoice Generated : "
                + order.getOrderId());

    }

    @Override
    public void updateAnalytics(OrderMessageDTO order) {

        LOGGER.info("Analytics Updated : "
                + order.getOrderId());

    }

    @Override
    public void notifyAdmin(OrderMessageDTO order) {

        LOGGER.info("Admin Notified : "
                + order.getOrderId());

    }

}