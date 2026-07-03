package lk.jiat.techmart.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

import lk.jiat.techmart.dto.OrderMessageDTO;
import lk.jiat.techmart.service.NotificationService;

import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                        propertyValue = "jms/OrderQueue"
                ),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "jakarta.jms.Queue"
                )
        }
)
public class OrderMessageListener implements MessageListener {

    private static final Logger LOGGER =
            Logger.getLogger(OrderMessageListener.class.getName());

    @Inject
    private NotificationService notificationService;

    @Override
    public void onMessage(Message message) {

        try {

            ObjectMessage objectMessage = (ObjectMessage) message;

            OrderMessageDTO order =
                    (OrderMessageDTO) objectMessage.getObject();

            LOGGER.info("======================================");
            LOGGER.info("JMS Message Received");
            LOGGER.info("Order ID : " + order.getOrderId());
            LOGGER.info("======================================");

            notificationService.sendOrderNotification(order);

        } catch (Exception e) {

            LOGGER.severe(e.getMessage());

        }

    }
}