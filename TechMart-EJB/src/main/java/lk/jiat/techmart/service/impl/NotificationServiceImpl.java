package lk.jiat.techmart.service.impl;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;

import lk.jiat.techmart.dto.OrderMessageDTO;
import lk.jiat.techmart.service.NotificationService;

import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER =
            Logger.getLogger(
                    NotificationServiceImpl.class.getName()
            );


    @Override
    @Asynchronous
    public void sendOrderNotification(OrderMessageDTO order) {

        long startTime = System.nanoTime();

        LOGGER.warning(
                "ASYNC NOTIFICATION STARTED - Order ID : "
                        + order.getOrderId()
        );

        try {



            try {

                sendCustomerEmail(order);

            } catch (Exception e) {

                LOGGER.log(
                        Level.SEVERE,
                        "Customer email notification failed for Order ID : "
                                + order.getOrderId(),
                        e
                );
            }



            try {

                generateInvoice(order);

            } catch (Exception e) {

                LOGGER.log(
                        Level.SEVERE,
                        "Invoice generation failed for Order ID : "
                                + order.getOrderId(),
                        e
                );
            }




            try {

                updateAnalytics(order);

            } catch (Exception e) {

                LOGGER.log(
                        Level.SEVERE,
                        "Analytics update failed for Order ID : "
                                + order.getOrderId(),
                        e
                );
            }




            try {

                notifyAdmin(order);

            } catch (Exception e) {

                LOGGER.log(
                        Level.SEVERE,
                        "Admin notification failed for Order ID : "
                                + order.getOrderId(),
                        e
                );
            }

        } finally {

            long endTime = System.nanoTime();

            long executionTime =
                    (endTime - startTime) / 1_000_000;

            LOGGER.warning(
                    "ASYNC NOTIFICATION COMPLETED - Order ID : "
                            + order.getOrderId()
                            + " - Execution Time : "
                            + executionTime
                            + " ms"
            );
        }
    }


    @Override
    public void sendCustomerEmail(OrderMessageDTO order) {

        LOGGER.warning(
                "Customer Email Sent : "
                        + order.getCustomerName()
        );
    }


    @Override
    public void generateInvoice(OrderMessageDTO order) {

        LOGGER.warning(
                "Invoice Generated : "
                        + order.getOrderId()
        );
    }


    @Override
    public void updateAnalytics(OrderMessageDTO order) {

        LOGGER.warning(
                "Analytics Updated : "
                        + order.getOrderId()
        );
    }


    @Override
    public void notifyAdmin(OrderMessageDTO order) {

        LOGGER.warning(
                "Admin Notified : "
                        + order.getOrderId()
        );
    }
}